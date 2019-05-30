package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import juanocampo.myapplication.com.domain.IPaging

class MovieViewModelFactory(private val paging: IPaging) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MovieViewModel::class.java.isAssignableFrom(modelClass)) return MovieViewModel(paging) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}