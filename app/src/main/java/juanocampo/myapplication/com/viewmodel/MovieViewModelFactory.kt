package juanocampo.myapplication.com.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import juanocampo.myapplication.com.data.IRepository

class MovieViewModelFactory(private val repository: IRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (MovieViewModel::class.java.isAssignableFrom(modelClass)) return MovieViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class") as Throwable
    }
}