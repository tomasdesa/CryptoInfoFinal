package ipvc.estg.cryptoinfofinal.APIMarket

import com.google.gson.annotations.SerializedName

data class TimeStampsPrices (


    var currency : String,
    var timestamps : List<String>,
    var prices : List<Double>

)


