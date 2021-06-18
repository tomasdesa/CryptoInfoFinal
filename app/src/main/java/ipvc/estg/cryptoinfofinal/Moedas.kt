package ipvc.estg.cryptoinfofinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import ipvc.estg.cryptoinfofinal.APIMarket.Endpoints
import ipvc.estg.cryptoinfofinal.APIMarket.Moeda
import ipvc.estg.cryptoinfofinal.APIMarket.ServiceBuilder
import ipvc.estg.cryptoinfofinal.APIUser.Endpoint
import ipvc.estg.cryptoinfofinal.APIUser.MoedaFavorita
import ipvc.estg.cryptoinfofinal.APIUser.ServiceBuilder1
import ipvc.estg.cryptoinfofinal.Adapter.MoedaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Moedas : AppCompatActivity() , MoedaAdapter.OnMoedaClickListener {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moedas)
        Log.v("api", "hello")

        val idUser = intent.getStringExtra("id_user")
        val username = intent.getStringExtra("username")
        val id_user: Int = idUser!!.toInt()


        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        val imgMenu = findViewById<ImageView>(R.id.imgmenu)

        val MoedaF = findViewById<ImageView>(R.id.moedaFavoritas)
        MoedaF.setOnClickListener {
            val popupMenu: PopupMenu = PopupMenu(this,MoedaF)
            popupMenu.menuInflater.inflate(R.menu.menu_nav,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.moedaFavoritasmenu ->{

                        val request1 = ServiceBuilder1.buildService(Endpoint::class.java)
                        val call1 = request1.moedafavoritaUser(id_user)
                        var i:Int=0
                        var stringMoeda:String =""

                        call1.enqueue(object : Callback<List<MoedaFavorita>> {
                            override fun onResponse(call: Call<List<MoedaFavorita>>, response: Response<List<MoedaFavorita>>) {

                                if (response.isSuccessful) {
                                    Log.v("check", response.toString())
                                    val c: List<MoedaFavorita> = response.body()!!

                                    for (moeda in c){

                                        if(i==0) {
                                            stringMoeda = moeda.idAPI
                                        }else{
                                            stringMoeda = stringMoeda + "," +moeda.idAPI
                                        }
                                        i++

                                    }

                                    val request6 = ServiceBuilder.buildService(Endpoints::class.java)

                                    val call6 = request6.getMoedasFavoritas(stringMoeda)
                                    val recyclerView = findViewById<RecyclerView>(R.id.recyclermarker)


                                    call6.enqueue(object : Callback<List<Moeda>> {
                                        override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {

                                            if(response.isSuccessful) {
                                                Log.v("response", response.toString())
                                                recyclerView.apply {
                                                    setHasFixedSize(true)
                                                    layoutManager = LinearLayoutManager (this@Moedas)
                                                    adapter = MoedaAdapter(response.body()!!,this@Moedas,this@Moedas)


                                                }

                                            }
                                        }

                                        override fun onFailure(call: Call<List<Moeda>>, t: Throwable) {
                                            Toast.makeText(this@Moedas, "${t.message}", Toast.LENGTH_LONG).show()

                                        }
                                    })

                                }
                            }

                            override fun onFailure(call: Call<List<MoedaFavorita>>, t: Throwable) {
                                Toast.makeText(this@Moedas, "${t.message}", Toast.LENGTH_SHORT).show()
                            }
                        })

                    }

                }
                true
            })
            popupMenu.show()
        }

        val navView = findViewById<NavigationView>(R.id.navDrawer)
        navView.itemIconTintList=null
        val headerview = navView.getHeaderView(0)
        val headertxt = headerview.findViewById<TextView>(R.id.headertxt)
        headertxt.text = getString(R.string.Bemvindo) +" " +username

        navView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home ->{
                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.Noticias ->{

                    /*val intent = Intent(this, ipvc.estg.commovtp1.Marker::class.java)
                    intent.putExtra("id_user", idUser)
                    startActivity(intent)*/
                }
                R.id.Moedas->{

                    drawerLayout.closeDrawer(GravityCompat.START)
                }
                R.id.Conversor->{

                    val intent = Intent(this, Conversor::class.java)
                    intent.putExtra("username",username)
                    intent.putExtra("id_user", idUser)
                    startActivity(intent)
                    finish()
                }
                R.id.logout ->{

                    val sharedPref: SharedPreferences =getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE)

                    val editor: SharedPreferences.Editor= sharedPref.edit()
                    editor.clear()
                    editor.commit()
                    editor.apply()

                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()


                }

            }
            // Close the drawer
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        imgMenu.setOnClickListener{
            drawerLayout.openDrawer(GravityCompat.START)
        }



        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val call = request.getMoedas()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclermarker)

        call.enqueue(object : Callback<List<Moeda>> {
            override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {
                Log.v("api", response.body()!!.toString())
                if(response.isSuccessful) {
                    Log.v("api", response.body()!!.toString())
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager (this@Moedas)
                        adapter = MoedaAdapter(response.body()!!,this@Moedas,this@Moedas)


                    }

                }
            }

            override fun onFailure(call: Call<List<Moeda>>, t: Throwable) {
                Toast.makeText(this@Moedas, "${t.message}", Toast.LENGTH_LONG).show()

            }
        })
    }

    override fun onMoedaClick(moeda: Moeda, position: Int) {
        val idUser = intent.getStringExtra("id_user")
        val username = intent.getStringExtra("username")

        val intent = Intent(this, MoedaInfo::class.java)
        intent.putExtra("id", moeda.id)
        intent.putExtra("nome", moeda.name)
        intent.putExtra("imagem", moeda.logo_url)
        intent.putExtra("simbolo", moeda.symbol)
        intent.putExtra("price", moeda.price)
        intent.putExtra("username",username)
        intent.putExtra("id_user", idUser)
        startActivity(intent)

    }

}