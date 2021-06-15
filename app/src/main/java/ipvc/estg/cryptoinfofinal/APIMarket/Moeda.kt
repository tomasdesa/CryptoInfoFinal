package ipvc.estg.cryptoinfofinal.APIMarket

import com.google.gson.annotations.*
data class Moeda (


       val id : String,
       val currency : String,
       val symbol : String,
       val name : String,
       val logo_url : String,
       val status : String,
       val price : Double,
       val price_date : String,
       val price_timestamp : String,
       val circulating_supply : Double,
       val max_supply : Double,
       val market_cap : Double,
       val market_cap_dominance : Double,
       val num_exchanges : Double,
       val num_pairs : Double,
       val num_pairs_unmapped : Double,
       val first_candle : String,
       val first_trade : String,
       val first_order_book : String,
       val rank : Int,
       val rank_delta : Int,
       val high : Double,
       val high_timestamp : String,
       @SerializedName("1h") val h1 : h1,
       @SerializedName("1d") val d1 : d1,
       @SerializedName("7d") val d7: d7
)

data class d7 (

        val volume : Double,
        val price_change : Double,
        val price_change_pct : Double,
        val volume_change : Double,
        val volume_change_pct : Double,
        val market_cap_change : Double,
        val market_cap_change_pct : Double
)
data class d1 (

        val volume : Double,
        val price_change : Double,
        val price_change_pct : Double,
        val volume_change : Double,
        val volume_change_pct : Double,
        val market_cap_change : Double,
        val market_cap_change_pct : Double
)
data class h1 (

        val volume : Double,
        val price_change : Double,
        val price_change_pct : Double,
        val volume_change : Double,
        val volume_change_pct : Double,
        val market_cap_change : Double,
        val market_cap_change_pct : Double
)
