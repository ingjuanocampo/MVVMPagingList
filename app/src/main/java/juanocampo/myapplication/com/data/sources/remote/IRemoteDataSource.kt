package juanocampo.myapplication.com.data.sources.remote

import juanocampo.myapplication.com.data.sources.remote.model.MovieResponse
import java.io.IOException


interface IRemoteDataSource {
    @Throws(IOException::class)
    fun fetchMovies(page: Int = 1): List<MovieResponse>
}