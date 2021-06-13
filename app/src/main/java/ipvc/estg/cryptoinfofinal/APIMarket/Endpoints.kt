package ipvc.estg.cryptoinfofinal.APIMarket


import okhttp3.ResponseBody
import retrofit2.http.*
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.Response

interface Endpoints {



    @GET("/listings/latest?CMC_PRO_API_KEY=9c6fd1d2-2deb-47d6-a524-440e18194cae&start=1&limit=10")
    fun  getMoedas(): Call<Data>

    @GET("/user/{id}")
    fun getUserById(@Path("id") id:Int): Call<Moeda>

    @FormUrlEncoded
    @POST("/myslim/API/user/login")
    fun login(@Field("username") username:String?,
              @Field("password") password:String?): Call<OutputPost>

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