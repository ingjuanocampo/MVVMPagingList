package juanocampo.myapplication.com.model.sources.remote.service

import juanocampo.myapplication.com.model.domain.MovieResponse

class MovieDBService(private val api: MovieDBApi): IMovieService {

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