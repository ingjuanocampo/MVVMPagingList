package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.ViewModel
import juanocampo.myapplication.com.model.IRepository

class MovieViewModel(private val iRepository: IRepository) : ViewModel() {

    init {
        iRepository.requestMoviesByPage(1)
    }

    fun requestMorePages(page: Int) {
        iRepository.requestMoviesByPage(page)

    }

    fun getMoviesListLiveData() = iRepository.getMoviesListObservable()
}