package juanocampo.myapplication.com.model

import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Resource

interface IRepository {

    suspend fun requestMoviesByPage(page: Int = 0): Resource<List<Movie>>
}