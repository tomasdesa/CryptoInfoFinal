package ipvc.estg.cryptoinfofinal.APIMarket

import com.google.gson.annotations.SerializedName

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
       val d1 : d1,
       val d30 : d30
)
