package ipvc.estg.cryptoinfofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.cryptoinfofinal.APIMarket.Endpoints
import ipvc.estg.cryptoinfofinal.APIMarket.Moeda
import ipvc.estg.cryptoinfofinal.APIMarket.ServiceBuilder
import ipvc.estg.cryptoinfofinal.Adapter.MoedaAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Moedas : AppCompatActivity() , MoedaAdapter.OnMoedaClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moedas)
        Log.v("api", "hello")

        val idUser = getIntent().getStringExtra("id_user")
        val username = getIntent().getStringExtra("username")

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val call = request.getMoedas()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclermarker)

        call.enqueue(object : Callback<List<Moeda>> {
            override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {
                Toast.makeText(this@Moedas, response.toString(), Toast.LENGTH_LONG).show()
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
        val idUser = getIntent().getStringExtra("id_user")
        //Toast.makeText(this, nota.titulo, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MoedaInfo::class.java)
        intent.putExtra("id", moeda.id)
        intent.putExtra("id_user",idUser )
        startActivity(intent)
        finish()
    }
}