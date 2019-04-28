package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Resource
import juanocampo.myapplication.com.model.domain.Status
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType
import kotlinx.coroutines.*

class MovieViewModel(
    private val iRepository: IRepository,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val errorLiveData = MutableLiveData<String>()
    val movieListLiveData = MutableLiveData<ArrayList<RecyclerViewType>>()
    private val loaderLiveData = MutableLiveData<Boolean>()
    private var pageNumber = 1
    private val items: ArrayList<RecyclerViewType> = ArrayList()

    private val loaderItem = object : RecyclerViewType {
        override fun getDelegateId() = Movie.MOVIE_LOADER.hashCode()
        override fun getViewType() = Movie.MOVIE_LOADER.hashCode()
    }

    fun fetchMoviesByPage() {
        GlobalScope.launch(ioDispatcher) {
            syncRepository()
        }
    }

    @Synchronized
    @WorkerThread
    private suspend fun syncRepository() {
        if (loaderLiveData.value != true) {
            loaderLiveData.postValue(true)
            if (!items.contains(loaderItem)) {
                addItemAndNotify(loaderItem)
            }

            val response = iRepository.requestMoviesByPage(pageNumber)
            when {
                response.status == Status.SUCCESS -> {
                    handleSuccessCase(response)
                }
                response.status == Status.LOADING -> loaderLiveData.postValue(true)
                else -> {
                    handleErrorCase(response)
                }
            }
        }
    }

    @WorkerThread
    private suspend fun handleSuccessCase(response: Resource<List<Movie>>) {
        items.remove(loaderItem)
        loaderLiveData.postValue(false)
        response.info?.let {
            addItemsAndNotify(it)
        }
        pageNumber++
    }

    @WorkerThread
    private suspend fun handleErrorCase(response: Resource<List<Movie>>) {
        loaderLiveData.postValue(false)
        removeItemsAndNotify(loaderItem)
        errorLiveData.postValue(response.message)
    }

    @WorkerThread
    private suspend fun addItemAndNotify(item: RecyclerViewType) {
        items.add(item)
        publishUIResults()
    }

    @WorkerThread
    private suspend fun addItemsAndNotify(itemsToAdd: List<RecyclerViewType>) {
        items.addAll(itemsToAdd)
        publishUIResults()
    }

    @WorkerThread
    private suspend fun removeItemsAndNotify(item: RecyclerViewType) {
        items.remove(item)
        publishUIResults()
    }

    @UiThread
    private suspend fun publishUIResults() {
        withContext(mainDispatcher) {
            movieListLiveData.value = items
        }
    }
}