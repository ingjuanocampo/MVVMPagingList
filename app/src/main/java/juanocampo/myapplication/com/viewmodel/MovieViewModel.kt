package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import juanocampo.myapplication.com.data.IRepository
import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.data.domain.Resource
import juanocampo.myapplication.com.data.domain.Status
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType
import juanocampo.myapplication.com.view.model.MovieRecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieViewModel(
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val job: Job = Job()
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = job + ioDispatcher

    val errorLiveData = MutableLiveData<String>()
    val movieListLiveData = MutableLiveData<ArrayList<RecyclerViewType>>()
    private var isLoading = false
    private val items: ArrayList<RecyclerViewType> = ArrayList()


    private val loaderItem = object : RecyclerViewType {
        override fun getDelegateId() = MovieRecyclerView.MOVIE_LOADER.hashCode()
        override fun getViewType() = MovieRecyclerView.MOVIE_LOADER.hashCode()
    }

    fun fetchMoviesByPage() {
        launch {
            syncRepository()
        }
    }

    @WorkerThread
    private suspend fun syncRepository() {
        if (!isLoading) {
            isLoading = true
            if (!items.contains(loaderItem)) {
                addItemAndNotify(loaderItem)
            }



        }
    }

    @WorkerThread
    private suspend fun handleSuccessCase(response: Resource<List<Movie>>) {
        items.remove(loaderItem)
        isLoading = false
        response.info?.let {
            addItemsAndNotify(it)
        }
        pageNumber++
    }

    @WorkerThread
    private suspend fun handleErrorCase(response: Resource<List<Movie>>) {
        isLoading = false
        removeItemsAndNotify(loaderItem)
        publishUIResults(errorLiveData, response.message)
    }

    @WorkerThread
    private suspend fun addItemAndNotify(item: RecyclerViewType) {
        items.add(item)
        publishUIResults(movieListLiveData, items)
    }

    @WorkerThread
    private suspend fun addItemsAndNotify(itemsToAdd: List<RecyclerViewType>) {
        items.addAll(itemsToAdd)
        publishUIResults(movieListLiveData, items)
    }

    @WorkerThread
    private suspend fun removeItemsAndNotify(item: RecyclerViewType) {
        items.remove(item)
        publishUIResults(movieListLiveData, items)
    }

    @UiThread
    private suspend fun <T> publishUIResults(liveData: MutableLiveData<T>, data: T) {
        withContext(mainDispatcher) {
            liveData.value = data
        }
    }
}