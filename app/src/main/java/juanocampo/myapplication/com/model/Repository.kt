package juanocampo.myapplication.com.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Resource
import juanocampo.myapplication.com.model.sources.remote.IRemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.mapper.MovieMapper
import java.lang.Exception

class Repository(private val iRemoteDataSource: IRemoteDataSource,
                 private val iMapper: MovieMapper): IRepository {


    private val results =  MutableLiveData<Resource<List<Movie>>>()
    private var pageRequested :Int = 1

    override fun getMoviesListObservable(): LiveData<Resource<List<Movie>>> {
        return results
    }

    override fun requestMoviesByPage(page: Int) {
        if (pageRequested == page) {
            return
        }
        try {
            val fetchedItems = iRemoteDataSource.fetchMovies(page)
            if (fetchedItems.isNullOrEmpty()) {
                results.value = Resource.error("could not load info, try later")
            } else {
                val mappedItems = iMapper.mapResponseToAppModel(fetchedItems)
                results.value = Resource.success(mappedItems)
            }
        } catch (e: Exception) {
            results.value = Resource.error(e.message?: "Something went wrong ")
        } finally {
            pageRequested = page
        }
    }
}