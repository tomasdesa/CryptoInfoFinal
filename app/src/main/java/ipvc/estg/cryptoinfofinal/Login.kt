
package ipvc.estg.cryptoinfofinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import ipvc.estg.cryptoinfofinal.APIUser.Endpoint
import ipvc.estg.cryptoinfofinal.APIUser.OutputPost
import ipvc.estg.cryptoinfofinal.APIUser.ServiceBuilder1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val request = ServiceBuilder1.buildService(Endpoint::class.java)

        val usernameText = findViewById<TextView>(R.id.username)

        val passwordText = findViewById<TextView>(R.id.password)




        val sharedPref:SharedPreferences=getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        val ischecked = sharedPref.getBoolean(getString(R.string.button),false)
        Log.d("shared", "Read $ischecked")

        if(ischecked){

            findViewById<CheckBox>(R.id.loginischecked).isChecked=true
            val user=sharedPref.getString(getString(R.string.username), "")
            val pass=sharedPref.getString(getString(R.string.password), "")

            val call = request.login(user, pass)

            call.enqueue(object : Callback<OutputPost> {
                override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                    if (response.isSuccessful) {

                        val c: OutputPost = response.body()!!
                        Toast.makeText(this@Login,c.MSG,Toast.LENGTH_SHORT).show()
                        Moedas(c.id,user)
                        finish()
                    }
                }

                override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                    Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })



        }

        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener {



            val user = usernameText.text.toString()
            val pass = passwordText.text.toString()



            if(user == "") {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.campoutilizador),
                    Toast.LENGTH_LONG).show()
            }
            else if(pass == "") {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.campopassword),
                    Toast.LENGTH_LONG).show()
            }
            else {
                val call = request.login(user, pass)
                val view= findViewById<CheckBox>(R.id.loginischecked)

                call.enqueue(object : Callback<OutputPost> {
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                        if (response.isSuccessful) {
                            if (view.isChecked() ) {
                                checkboxisClicked(view)
                            }
                            val c: OutputPost = response.body()!!
                            Toast.makeText(this@Login,c.MSG,Toast.LENGTH_SHORT).show()

                            if (c.status=="true"){
                                Moedas(c.id,user)
                                finish()
                            }

                        }
                    }

                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                        Toast.makeText(this@Login, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
            }
        }

        /*val notas = findViewById<Button>(R.id.Notasl)
        notas.setOnClickListener(){
            val intent = Intent(this, Notas::class.java)
            startActivity(intent)
        }*/
    }
    fun Moedas(iduser: String, user:String?) {
        val intent = Intent(this, Moedas::class.java)
        intent.putExtra("id_user", iduser)
        intent.putExtra("username", user)
        startActivity(intent)
    }
    fun checkboxisClicked(view: View){
        if(view is CheckBox){
            val usernameText = findViewById<TextView>(R.id.username)

            val passwordText = findViewById<TextView>(R.id.password)

            val sharedPref: SharedPreferences= getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE)
            with (sharedPref.edit()){
                putBoolean(getString(R.string.button), view.isChecked)
                putString(getString(R.string.username), usernameText.text.toString())
                putString(getString(R.string.password), passwordText.text.toString())
                commit()
            }
        }
    }
}
