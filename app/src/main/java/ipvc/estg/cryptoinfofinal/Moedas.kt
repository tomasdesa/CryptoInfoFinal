package ipvc.estg.cryptoinfofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.cryptoinfofinal.APIMarket.Data
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

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val call = request.getMoedas()
        val recyclerView = findViewById<RecyclerView>(R.id.coin_layout)

        Toast.makeText(this@Moedas, "Ocorreu um erro", Toast.LENGTH_SHORT).show()

        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if(response.isSuccessful) {
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager (this@Moedas)
                        adapter = MoedaAdapter(response.body()!!,this@Moedas)


                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@Moedas, "Ocorreu um erro", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onMoedaClick(moeda: Moeda, position: Int) {
        TODO("Not yet implemented")
    }
}