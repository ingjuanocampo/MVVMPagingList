package juanocampo.myapplication.com.view.adapter

import android.support.v4.util.SparseArrayCompat
import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.utils.delegate.BaseAdapter
import juanocampo.myapplication.com.utils.delegate.appendDelegate

class MovieListAdapter: BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(Movie.MOVIE_ITEM.hashCode(), MovieDelegateAdapter())
        delegateAdapters.appendDelegate(Movie.MOVIE_LOADER.hashCode(), LoaderDelegateAdapter())
    }

}