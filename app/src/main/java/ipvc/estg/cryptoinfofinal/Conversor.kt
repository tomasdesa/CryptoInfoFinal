package ipvc.estg.cryptoinfofinal

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton
import com.scrounger.countrycurrencypicker.library.Country
import com.scrounger.countrycurrencypicker.library.Currency
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener
import ipvc.estg.cryptoinfofinal.APIMarket.Endpoints
import ipvc.estg.cryptoinfofinal.APIMarket.Moeda
import ipvc.estg.cryptoinfofinal.APIMarket.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Conversor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        val currencypicker = findViewById<CountryCurrencyButton>(R.id.picker)
        val spinner =findViewById<Spinner>(R.id.spinner)
        currencypicker.setOnClickListener(object : CountryCurrencyPickerListener {
            override fun onSelectCountry(country: Country) {
                if (country.currency == null) {
                    Toast.makeText(
                        this@Conversor,
                        String.format(
                            "name: %s\ncode: %s",
                            country.name,
                            country.code
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@Conversor,
                        String.format(
                            "name: %s\ncurrencySymbol: %s",
                            country.name,
                            country.currency!!.symbol
                        ),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onSelectCurrency(currency: Currency?) {}
        })

        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val call = request.getConversor()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclermarker)

        call.enqueue(object : Callback<List<Moeda>> {
            override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {
                //Toast.makeText(this@Conversor, response.toString(), Toast.LENGTH_LONG).show()
                if(response.isSuccessful) {
                    val arrayList: ArrayList<String> = ArrayList()

                    var moeda :List<Moeda> = response.body()!!

                    for (moeda in moeda){

                        arrayList.add(moeda.name)

                    }



                }
            }

            override fun onFailure(call: Call<List<Moeda>>, t: Throwable) {
                Toast.makeText(this@Conversor, "${t.message}", Toast.LENGTH_LONG).show()

            }
        })


    }




}