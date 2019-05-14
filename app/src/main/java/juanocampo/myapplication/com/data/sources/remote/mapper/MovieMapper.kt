package juanocampo.myapplication.com.data.sources.remote.mapper

import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.data.sources.remote.domain.MovieResponse

class MovieMapper(private val baseImageUrl: String): IMapper<List<MovieResponse>, List<Movie>> {

    override fun mapResponseToAppModel(toParse: List<MovieResponse>): List<Movie> {
        val movieList = ArrayList<Movie>()
        if (toParse.isEmpty()) {
            return emptyList()
        }
        toParse.forEach {
            movieList.add(it.toMovie(baseImageUrl))
        }
        return movieList
    }
}