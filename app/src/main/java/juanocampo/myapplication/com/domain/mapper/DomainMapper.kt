package juanocampo.myapplication.com.domain.mapper

import juanocampo.myapplication.com.commons.IMapper
import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.view.model.MovieRecyclerView

class DomainMapper: IMapper<List<Movie>, List<MovieRecyclerView>> {

    override fun invoke(toParse: List<Movie>): List<MovieRecyclerView> {

        val movieList = ArrayList<Movie>()
        if (toParse.isEmpty()) {
            return emptyList()
        }
        toParse.forEach {
            movieList.add(it.toMovieRecyclerView())
        }
        return movieList
    }
}

private fun Movie.toMovieRecyclerView(): Movie {

}
