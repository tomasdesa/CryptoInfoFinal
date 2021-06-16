package ipvc.estg.cryptoinfofinal

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ahmadrosid.svgloader.SvgLoader
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.squareup.picasso.Picasso
import ipvc.estg.cryptoinfofinal.APIMarket.Endpoints
import ipvc.estg.cryptoinfofinal.APIMarket.ServiceBuilder
import ipvc.estg.cryptoinfofinal.APIMarket.TimeStampsPrices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToLong


class MoedaInfo : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moeda_info)

        val idM = intent.getStringExtra("id")
        val imagemMoeda = intent.getStringExtra("imagem")
        val nomeMoeda = intent.getStringExtra("nome")
        var preçoMoeda = intent.getDoubleExtra("price",0.0)
        var PreçoMoed= preçoMoeda.roundToLong()

        supportActionBar?.title = nomeMoeda

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        var formatted = current.format(formatter)
        //Toast.makeText(this@MoedaInfo, formatted, Toast.LENGTH_LONG).show()
        //formatted =formatted + "T00%3A00%3A00Z"

        val imagelogo= findViewById<ImageView>(R.id.LogoMoeda)
        val priceMoeda=findViewById<TextView>(R.id.Price)
        val NameMoeda=findViewById<TextView>(R.id.NameMoeda)
        val conversor =findViewById<Button>(R.id.conversor)

        if(imagemMoeda.toString().contains(".svg")){
            SvgLoader.pluck()
                .with(this)
                .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                .load(imagemMoeda, imagelogo)
        }
        else if (imagemMoeda.toString().contains(".png")){
            Picasso.get().load(imagemMoeda).into(imagelogo)
        }

        NameMoeda.text = nomeMoeda
        priceMoeda.text= PreçoMoed.toString()


        val mpchart:LineChart=findViewById(R.id.chartView)
        val request = ServiceBuilder.buildService(Endpoints::class.java)

        val call = request.getMoedaInfo(idM,formatted+ "T00%3A00%3A00Z")

        call.enqueue(object : Callback<List<TimeStampsPrices>> {
            override fun onResponse(call: Call<List<TimeStampsPrices>>, response: Response<List<TimeStampsPrices>>) {
                //Toast.makeText(this@MoedaInfo, response.toString(), Toast.LENGTH_LONG).show()
                if(response.isSuccessful) {
                    Log.v("apiMoeda", response.body()!!.toString())


                    var moeda :List<TimeStampsPrices> = response.body()!!

                    val dataSets: ArrayList<ILineDataSet> = ArrayList()
                    val prices: ArrayList<Entry> = ArrayList()


                    var i:Float=0F
                    var set1: LineDataSet

                    for(moeda in moeda) {
                        var yourArray: MutableList<String> = ArrayList()

                        for(moedaprices in moeda.prices) {
                            prices.add(Entry(i, moedaprices.toFloat()))
                            i++
                        }
                        //Log.v("I", i.toString())

                        for(moedatimes in moeda.timestamps) {
                            val string: String = moedatimes
                            val split=  moedatimes.split("T")
                            val horaz=split[1]
                            val split2=  horaz.split("Z")
                            val hora =split2[0]
                            yourArray.add(hora)
                        }

                        val xAxisValues: List<String> = ArrayList(yourArray)


                        set1 = LineDataSet(prices, "Preço")
                        set1.color = Color.rgb(65, 168, 121)
                        set1.valueTextColor = Color.rgb(55, 70, 73)
                        set1.valueTextSize = 12f
                        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
                        set1.setDrawHighlightIndicators(true)
                        set1.highLightColor=Color.BLACK
                        dataSets.add(set1)

                        mpchart.setTouchEnabled(true)
                        mpchart.isDragEnabled = true
                        mpchart.setScaleEnabled(false)
                        mpchart.setPinchZoom(false)
                        mpchart.setDrawGridBackground(false)
                        mpchart.extraLeftOffset = 30F
                        mpchart.extraRightOffset = 30F
                        mpchart.xAxis.setDrawGridLines(true)
                        mpchart.axisLeft.setDrawGridLines(true)
                        mpchart.axisRight.setDrawGridLines(true)
                        mpchart.isHighlightPerTapEnabled = true


                        val rightYAxis: YAxis = mpchart.axisRight
                        rightYAxis.isEnabled = true
                        val leftYAxis: YAxis = mpchart.axisLeft
                        leftYAxis.isEnabled = true
                        val topXAxis: XAxis = mpchart.xAxis
                        topXAxis.isEnabled = false
                        val xAxis: XAxis = mpchart.xAxis
                        xAxis.granularity = 0.1f
                        xAxis.setCenterAxisLabels(false)
                        xAxis.isEnabled = true
                        xAxis.setDrawGridLines(false)
                        xAxis.textSize=12f
                        xAxis.position = XAxis.XAxisPosition.BOTTOM

                        mpchart.xAxis.valueFormatter = IndexAxisValueFormatter(xAxisValues)
                        val data = LineData(dataSets)
                        mpchart.data = data
                        mpchart.animateX(2000)
                        mpchart.invalidate()
                        mpchart.legend.isEnabled = false
                        mpchart.description.isEnabled = false
                        mpchart.xAxis.setLabelCount(i.toInt(), true)
                    }

                }
            }

            override fun onFailure(call: Call<List<TimeStampsPrices>>, t: Throwable) {
                Toast.makeText(this@MoedaInfo, "${t.message}", Toast.LENGTH_LONG).show()

            }
        })

        conversor.setOnClickListener(){
            val intent = Intent(this, Conversor::class.java)
            startActivity(intent)
        }


    }


}