package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Resource
import juanocampo.myapplication.com.model.domain.Status
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieViewModel(private val iRepository: IRepository,
                     private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    val loaderLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val movieListLiveData = MutableLiveData<ArrayList<RecyclerViewType>>()
    private var pageNumber = 1

    private val loaderItem = object : RecyclerViewType {
        override fun getDelegateId() = Movie.MOVIE_LOADER.hashCode()
        override fun getViewType() = Movie.MOVIE_LOADER.hashCode()
    }

    private val items: ArrayList<RecyclerViewType> = ArrayList()

    fun fetchMoviesByPage() {
        GlobalScope.launch(mainDispatcher) {
            if (loaderLiveData.value != true) {
                loaderLiveData.value = true
                addItemAndNotify(loaderItem)

                val response = iRepository.requestMoviesByPage(pageNumber)
                when {
                    response.status == Status.SUCCESS -> {
                       handleSuccessCase(response)
                    }
                    response.status == Status.LOADING -> loaderLiveData.value = true
                    else -> {
                        handleErrorCase(response)
                    }
                }
                pageNumber++
            }
        }

    }

    private fun handleSuccessCase(response: Resource<List<Movie>>) {
        items.remove(loaderItem)
        loaderLiveData.value = false
        response.info?.let {
            addItemsAndNotify(it)
        }
    }

    private fun handleErrorCase(response: Resource<List<Movie>>) {
        loaderLiveData.value = false
        removeItemsAndNotify(loaderItem)
        errorLiveData.value = response.message
    }

    private fun addItemAndNotify(item: RecyclerViewType) {
        items.add(item)
        movieListLiveData.value = items
    }

    private fun addItemsAndNotify(itemsToAdd: List<RecyclerViewType>) {
        items.addAll(itemsToAdd)
        movieListLiveData.value = items
    }

    private fun removeItemsAndNotify(item: RecyclerViewType) {
        items.remove(item)
        movieListLiveData.value = items
    }
}