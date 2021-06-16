package ipvc.estg.cryptoinfofinal.APIMarket


import retrofit2.http.*
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.Response

interface Endpoints {



    @GET("/v1/currencies/ticker?key=62b6fc652e8520947713e6123cad26d94f699902&interval=1h,1d,7d&convert=EUR&per-page=15&page=1&sort=rank")
    fun  getMoedas(): Call<List<Moeda>>

    @GET("/v1/currencies/sparkline?key=62b6fc652e8520947713e6123cad26d94f699902")
    fun  getMoedaInfo(@Query("ids") id:String?,@Query("start",encoded = true) start:String): Call<List<TimeStampsPrices>>

    @GET("/v1/currencies/ticker?key=62b6fc652e8520947713e6123cad26d94f699902&interval=1h,1d,7d&convert=EUR&per-page=100&page=1&sort=rank")
    fun  getConversor(): Call<List<Moeda>>
    /*@GET("/user/{id}")
    fun getUserById(@Path("id") id:Int): Call<Moeda>

    @FormUrlEncoded
    @POST("/myslim/API/user/login")
    fun login(@Field("username") username:String?,
              @Field("password") password:String?): Call<OutputPost>
*/
    /*@GET("/myslim/API/marker")
    fun  getMarkers(): Call<List<marker>>


    @GET("/myslim/API/markerUser/{id_user}")
    fun getMarkerByIdUser(@Path("id_user") id:Int): Call<List<marker>>

    @GET("/myslim/API/marker/{id}")
    fun getMarkerById(@Path("id") id:Int?): Call<marker>



    @POST("/myslim/API/markerDelete/{id}")
    fun DeleteMarker(@Path("id") id:Int?): Call<Outputmarker>

    @Multipart
    @POST("/myslim/API/postMarker")
    fun postMarker(@Part("titulo") titulo: RequestBody,
                   @Part("descricao") descricao: RequestBody,
                   @Part("latitude") latitude: RequestBody,
                   @Part("longitude") longitude: RequestBody,
                   @Part imagem: MultipartBody.Part,
                   @Part("tipoproblema") tipoproblema: RequestBody,
                   @Part("id_user") id_user: Int?): Call<Outputmarker>

    @FormUrlEncoded
    @POST("/myslim/API/markerPut/{id}")
    fun updateMarker(@Path("id") id:Int,
                     @Field("titulo") titulo:String?,
                     @Field("descricao") descricao:String?,
                     @Field("tipoproblema") tipoproblema:String?):Call<Outputmarker>*/
}