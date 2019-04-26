package juanocampo.myapplication.com.model.sources.remote.mapper

import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse
import javax.inject.Named

class MovieMapper(@Named("MovieDB_Image_URL") private val baseImageUrl: String): IMapper<List<MovieResponse>, List<Movie>> {

    override fun mapResponseToAppModel(toParse: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        toParse.forEach {
            movieList.add(it.toMovie(baseImageUrl))
        }
        return movieList
    }
}