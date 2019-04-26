package juanocampo.myapplication.com.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import juanocampo.myapplication.com.R
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.utils.delegate.DelegateAdapter

class MovieDelegateAdapter : DelegateAdapter<MovieDelegateAdapter.ViewHolder, Movie> {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item, viewGroup, false)) {



    }
}