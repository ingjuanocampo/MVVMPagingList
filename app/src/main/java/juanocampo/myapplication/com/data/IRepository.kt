package juanocampo.myapplication.com.data

import juanocampo.myapplication.com.data.domain.RepositoryStates

interface IRepository {

    suspend fun requestMoviesByPage(page: Int = 0): RepositoryStates
}