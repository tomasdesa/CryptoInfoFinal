package ipvc.estg.cryptoinfofinal.APIMarket

import com.google.gson.annotations.*
data class Moeda (


       var id : String,
       var currency : String,
       var symbol : String,
       var name : String,
       var logo_url : String,
       var status : String,
       var price : Double,
       var price_date : String,
       var price_timestamp : String,
       var circulating_supply : Double,
       var max_supply : Double,
       var market_cap : Double,
       var market_cap_dominance : Double,
       var num_exchanges : Double,
       var num_pairs : Double,
       var num_pairs_unmapped : Double,
       var first_candle : String,
       var first_trade : String,
       var first_order_book : String,
       var rank : Int,
       var rank_delta : Int,
       var high : Double,
       var high_timestamp : String,
       @SerializedName("1h") var h1 : h1,
       @SerializedName("1d") var d1 : d1,
       @SerializedName("7d") var d7: d7
)

data class d7 (

        var volume : Double,
        var price_change : Double,
        var price_change_pct : Double,
        var volume_change : Double,
        var volume_change_pct : Double,
        var market_cap_change : Double,
        var market_cap_change_pct : Double
)
data class d1 (

        var volume : Double,
        var price_change : Double,
        var price_change_pct : Double,
        var volume_change : Double,
        var volume_change_pct : Double,
        var market_cap_change : Double,
        var market_cap_change_pct : Double
)
data class h1 (

        var volume : Double,
        var price_change : Double,
        var price_change_pct : Double,
        var volume_change : Double,
        var volume_change_pct : Double,
        var market_cap_change : Double,
        var market_cap_change_pct : Double
)
