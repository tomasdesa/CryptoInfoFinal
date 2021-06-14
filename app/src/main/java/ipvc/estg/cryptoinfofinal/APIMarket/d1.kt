package ipvc.estg.cryptoinfofinal.APIMarket

import com.google.gson.annotations.SerializedName


data class d1 (

	val volume : Double,
	val price_change : Double,
	val price_change_pct : Double,
	val volume_change : Double,
	val volume_change_pct : Double,
	val market_cap_change : Double,
	val market_cap_change_pct : Double
)