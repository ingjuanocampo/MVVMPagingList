package juanocampo.myapplication.com.view.adapter

import android.support.v4.util.SparseArrayCompat
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.utils.delegate.BaseAdapter
import juanocampo.myapplication.com.utils.delegate.appendDelegate
import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType

class MovieListAdapter: BaseAdapter() {

    private val loaderItem = object :RecyclerViewType {
        override fun getDelegateId() = Movie.MOVIE_LOADER.hashCode()
        override fun getViewType() = Movie.MOVIE_LOADER.hashCode()
    }

    init {
        delegateAdapters = SparseArrayCompat(2)
        delegateAdapters.appendDelegate(Movie.MOVIE_ITEM.hashCode(), MovieDelegateAdapter())
        delegateAdapters.appendDelegate(Movie.MOVIE_LOADER.hashCode(), LoaderDelegateAdapter())
    }

    fun addLoader() {
        addItem(loaderItem)
    }

    fun removeLoader() {
        if (items.contains(loaderItem)) {
            items.remove(loaderItem)
            notifyDataSetChanged()
        }
    }
}