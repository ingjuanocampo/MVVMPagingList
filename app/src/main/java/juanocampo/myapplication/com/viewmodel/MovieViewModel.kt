package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieViewModel(private val iRepository: IRepository,
                     private val mainDispatcher: CoroutineDispatcher = Dispatchers.Main) : ViewModel() {

    val loaderLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val movieListLiveData = MutableLiveData<List<Movie>>()

    fun fetchMoviesByPage(page: Int = 1) {
        GlobalScope.launch(mainDispatcher) {
            loaderLiveData.value = true
            val response = iRepository.requestMoviesByPage(page)

            when {
                response.status == Status.SUCCESS -> {
                    loaderLiveData.value = false
                    movieListLiveData.value = response.info
                }
                response.status == Status.LOADING -> loaderLiveData.value = true
                else -> {
                    loaderLiveData.value = false
                    errorLiveData.value = response.message
                }
            }
        }

    }
}