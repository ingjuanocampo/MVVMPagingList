package juanocampo.myapplication.com.data.sources.remote.service

import juanocampo.myapplication.com.data.sources.remote.model.MoviesDBResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBApi {

    @GET("/3/tv/popular")
    fun fetchMovies(@Query("api_key") key: String,
                    @Query("language") language: String,
                    @Query("page") page: Int): Call<MoviesDBResponse>

}