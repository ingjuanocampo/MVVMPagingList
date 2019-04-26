package juanocampo.myapplication.com.model.sources.remote

import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse


interface IRemoteDataSource {
    fun fetchMovies(page: Int = 1): List<MovieResponse>
}