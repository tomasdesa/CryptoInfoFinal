package ipvc.estg.cryptoinfofinal.APIMarket
import com.google.gson.annotations.SerializedName




data class USD (

	@SerializedName("price") val price : Double,
	@SerializedName("volume_24h") val volume_24h : Double,
	@SerializedName("percent_change_1h") val percent_change_1h : Double,
	@SerializedName("percent_change_24h") val percent_change_24h : Double,
	@SerializedName("percent_change_7d") val percent_change_7d : Double,
	@SerializedName("percent_change_30d") val percent_change_30d : Double,
	@SerializedName("percent_change_60d") val percent_change_60d : Double,
	@SerializedName("percent_change_90d") val percent_change_90d : Double,
	@SerializedName("market_cap") val market_cap : Double,
	@SerializedName("last_updated") val last_updated : String
)