package ipvc.estg.cryptoinfofinal


import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
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
        val ConverterButton= findViewById<Button>(R.id.converter)
        val textviewNumero= findViewById<EditText>(R.id.Numero)
        val Resultado=findViewById<TextView>(R.id.Resultado)
        var currency:String=""

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
                    currency=country.currency!!.symbol
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

        val arrayList: ArrayList<String> = ArrayList()

        call.enqueue(object : Callback<List<Moeda>> {
            override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {
                //Toast.makeText(this@Conversor, response.toString(), Toast.LENGTH_LONG).show()
                if(response.isSuccessful) {


                    var moeda :List<Moeda> = response.body()!!

                    for (moeda in moeda){

                        arrayList.add(moeda.currency)

                        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(applicationContext, R.layout.spinner_list ,arrayList)
                        adapter.setDropDownViewResource(R.layout.spinner_list)

                        spinner.adapter = adapter

                    }

                }
            }

            override fun onFailure(call: Call<List<Moeda>>, t: Throwable) {
                Toast.makeText(this@Conversor, "${t.message}", Toast.LENGTH_LONG).show()

            }
        })



        ConverterButton.setOnClickListener{

            val Crytpo: String = spinner.getSelectedItem().toString()
            val request = ServiceBuilder.buildService(Endpoints::class.java)
            var Numero: Int = Integer.parseInt(textviewNumero.text.toString())
            var preço:Double=0.0
            Log.v("Crypto", Crytpo)
            Log.v("Numero", Numero.toString())
            Log.v("moeda", currency)


            if (currency=="€"){
                currency="EUR"

            }else if(currency=="$"){
                currency="USD"
            }
            else if(currency=="EC$"){
                currency="XCD"
            }

            val call = request.getMoedaParaConverter(Crytpo, currency)




            call.enqueue(object : Callback<List<Moeda>> {
                override fun onResponse(call: Call<List<Moeda>>, response: Response<List<Moeda>>) {
                    //Toast.makeText(this@Conversor, response.toString(), Toast.LENGTH_LONG).show()
                    if(response.isSuccessful) {


                        var moeda :List<Moeda> = response.body()!!

                        for (moeda in moeda){

                            preço=moeda.price
                            var ResultadoNumero= Math.round(preço * Numero)
                            Resultado.text=ResultadoNumero.toString()+" "+currency

                        }
                    }
                }

                override fun onFailure(call: Call<List<Moeda>>, t: Throwable) {
                    Toast.makeText(this@Conversor, "${t.message}", Toast.LENGTH_LONG).show()

                }
            })




        }

    }




}