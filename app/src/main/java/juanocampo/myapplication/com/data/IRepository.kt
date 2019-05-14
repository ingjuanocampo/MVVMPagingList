package juanocampo.myapplication.com.data

import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.data.domain.Resource

interface IRepository {

    suspend fun requestMoviesByPage(page: Int = 0): Resource<List<Movie>>
}