package juanocampo.myapplication.com.data

import android.support.annotation.WorkerThread
import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.data.domain.Resource
import juanocampo.myapplication.com.data.sources.remote.IRemoteDataSource
import juanocampo.myapplication.com.data.sources.remote.mapper.MovieMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class Repository(private val iRemoteDataSource: IRemoteDataSource,
                 private val iMapper: MovieMapper,
                 private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): IRepository {


    @Synchronized
    override suspend fun requestMoviesByPage(page: Int): Resource<List<Movie>> {
        return withContext(ioDispatcher) {
            return@withContext fetchMoviesFromRemote(page)
        }
    }

    @WorkerThread
    private fun fetchMoviesFromRemote(page: Int): Resource<List<Movie>> {

        return try {
            val fetchedItems = iRemoteDataSource.fetchMovies(page)
            if (fetchedItems.isNullOrEmpty()) {
                Resource.error("could not load info, try later")
            } else {
                val mappedItems = iMapper.mapResponseToAppModel(fetchedItems)
                Resource.success(mappedItems)
            }
        } catch (e: Exception) {
            Resource.error(e.message?: "Something went wrong")
        }
    }
}