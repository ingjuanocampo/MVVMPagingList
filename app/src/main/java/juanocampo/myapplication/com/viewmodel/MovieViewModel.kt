package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.UiThread
import android.support.annotation.WorkerThread
import juanocampo.myapplication.com.data.domain.Status
import juanocampo.myapplication.com.domain.IPaging
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType
import juanocampo.myapplication.com.view.model.MovieRecyclerView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieViewModel(
    private val paging: IPaging,
    private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private var pagingJob: Job? = null
) : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = ioDispatcher

    val errorLiveData = MutableLiveData<String>()
    val listLiveData = MutableLiveData<ArrayList<RecyclerViewType>>()
    private val items: ArrayList<RecyclerViewType> = ArrayList()

    private val loaderItem = object : RecyclerViewType {
        override fun getDelegateId() = MovieRecyclerView.MOVIE_LOADER.hashCode()
        override fun getViewType() = MovieRecyclerView.MOVIE_LOADER.hashCode()
    }

    fun fetchMoviesByPage() {
        if (pagingJob == null || pagingJob?.isCompleted == true) {
            pagingJob = launch {
                addItemAndNotify(loaderItem)

                val pageResult = paging()
                when {
                    pageResult.status == Status.SUCCESS -> {
                        removeItemsAndNotify(loaderItem)
                        pageResult.info?.let {
                            addItemsAndNotify(it)
                        }
                    }
                    else -> {
                        removeItemsAndNotify(loaderItem)
                        errorLiveData.postValue(pageResult.message)
                    }
                }
            }
        }
    }

    @WorkerThread
    private suspend fun addItemsAndNotify(itemsToadd: List<MovieRecyclerView>) {
        items.addAll(itemsToadd)
        publishUIResults(listLiveData, items)
    }


    @WorkerThread
    private suspend fun addItemAndNotify(item: RecyclerViewType) {
        items.add(item)
        publishUIResults(listLiveData, items)
    }

    @WorkerThread
    private suspend fun removeItemsAndNotify(item: RecyclerViewType) {
        items.remove(item)
        publishUIResults(listLiveData, items)
    }

    @UiThread
    private suspend fun <T> publishUIResults(liveData: MutableLiveData<T>, data: T) {
        withContext(mainDispatcher) {
            liveData.value = data
        }
    }
}