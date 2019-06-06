package juanocampo.myapplication.com.data.sources.remote

import juanocampo.myapplication.com.data.sources.remote.model.MovieResponse
import juanocampo.myapplication.com.data.sources.remote.service.MovieDBApi

class RemoteDataSource(private val api: MovieDBApi): IRemoteDataSource {

    companion object {
        const val LANGUAGE = "en-US"
        const val API_KEY = "5d967c7c335764f39b1efbe9c5de9760"
    }

    override fun fetchMovies(page: Int): List<MovieResponse> {
        val request = api.fetchMovies(key = API_KEY, language = LANGUAGE, page = page)
        val apiResponse = request.execute()
        return if (apiResponse.isSuccessful && apiResponse.body() != null &&
            apiResponse.body()!!.results!= null) {
            apiResponse.body()!!.results!!
        } else {
            emptyList()
        }
    }


}