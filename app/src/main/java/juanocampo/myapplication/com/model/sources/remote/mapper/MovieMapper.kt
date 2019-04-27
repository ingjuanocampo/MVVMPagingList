package juanocampo.myapplication.com.model.sources.remote.mapper

import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse

class MovieMapper(private val baseImageUrl: String): IMapper<List<MovieResponse>, List<Movie>> {

    override fun mapResponseToAppModel(toParse: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        toParse.forEach {
            movieList.add(it.toMovie(baseImageUrl))
        }
        return movieList
    }
}