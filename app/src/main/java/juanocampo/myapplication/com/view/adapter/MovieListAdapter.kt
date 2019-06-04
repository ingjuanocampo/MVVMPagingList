package juanocampo.myapplication.com.view.adapter

import android.support.v4.util.SparseArrayCompat
import juanocampo.myapplication.com.utils.delegate.BaseAdapter
import juanocampo.myapplication.com.utils.delegate.appendDelegate
import juanocampo.myapplication.com.view.model.MovieRecyclerView

class MovieListAdapter: BaseAdapter() {

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(MovieRecyclerView.MOVIE_ITEM.hashCode(), MovieDelegateAdapter())
        delegateAdapters.appendDelegate(MovieRecyclerView.MOVIE_LOADER.hashCode(), LoaderDelegateAdapter())
    }

}