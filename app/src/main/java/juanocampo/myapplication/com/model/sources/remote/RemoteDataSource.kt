package juanocampo.myapplication.com.model.sources.remote

import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse
import juanocampo.myapplication.com.model.sources.remote.service.MovieDBApi

class RemoteDataSource(private val api: MovieDBApi): IRemoteDataSource {

    // http://image.tmdb.org/t/p/w780///s0w8JbuNNxL1YgaHeDWih12C3jG.jpg
// https://api.themoviedb.org/3/tv/popular?api_key=5d967c7c335764f39b1efbe9c5de9760&language=en-US&page=1

    companion object {
        const val LANGUAGE = "en-US"
        const val API_KEY = "5d967c7c335764f39b1efbe9c5de9760"
    }

    override fun fetchMovies(page: Int): List<MovieResponse> {
        val request = api.fetchMovies(key = API_KEY, language = LANGUAGE, page = page)
        val apiResponse = request.execute()
        return if (apiResponse.isSuccessful && apiResponse.body() != null) {
            apiResponse.body()!!.results
        } else {
            emptyList()
        }
    }


}