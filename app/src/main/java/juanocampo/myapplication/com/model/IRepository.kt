package juanocampo.myapplication.com.model

import android.arch.lifecycle.LiveData
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Resource

interface IRepository {

    fun getMoviesListObservable(): LiveData<Resource<List<Movie>>>

    fun requestMoviesByPage(page: Int = 0)
}