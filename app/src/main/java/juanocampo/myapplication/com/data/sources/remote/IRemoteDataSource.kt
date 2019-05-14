package juanocampo.myapplication.com.data.sources.remote

import juanocampo.myapplication.com.data.sources.remote.domain.MovieResponse
import java.io.IOException


interface IRemoteDataSource {
    @Throws(IOException::class)
    fun fetchMovies(page: Int = 1): List<MovieResponse>
}