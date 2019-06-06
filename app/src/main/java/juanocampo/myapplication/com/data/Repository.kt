package juanocampo.myapplication.com.data

import android.support.annotation.WorkerThread
import juanocampo.myapplication.com.data.domain.RepositoryStates
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
    override suspend fun requestMoviesByPage(page: Int): RepositoryStates {
        return withContext(ioDispatcher) {
            return@withContext fetchMoviesFromRemote(page)
        }
    }

    @WorkerThread
    private fun fetchMoviesFromRemote(page: Int): RepositoryStates {

        return try {
            val fetchedItems = iRemoteDataSource.fetchMovies(page)
            if (fetchedItems.isNullOrEmpty()) {
                RepositoryStates.Error("could not load info, try later")
            } else {
                val mappedItems = iMapper(fetchedItems)
                RepositoryStates.Success(mappedItems)
            }
        } catch (e: Exception) {
            RepositoryStates.Error(e.message?: "Something went wrong")
        }
    }
}