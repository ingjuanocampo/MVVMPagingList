package juanocampo.myapplication.com.model.sources.remote

import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse
import java.io.IOException


interface IRemoteDataSource {
    @Throws(IOException::class)
    fun fetchMovies(page: Int = 1): List<MovieResponse>
}