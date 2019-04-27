package juanocampo.myapplication.com.view.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import juanocampo.myapplication.com.R
import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.utils.delegate.DelegateAdapter
import java.lang.Exception

class MovieDelegateAdapter : DelegateAdapter<MovieDelegateAdapter.ViewHolder, Movie> {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, viewType: Movie) {
        viewHolder.bind(viewType)
    }

    class ViewHolder(viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.movie_item, viewGroup, false)) {

        val movieImage = itemView.findViewById<ImageView>(R.id.movie_image)
        val movieDescription = itemView.findViewById<TextView>(R.id.description)
        val movieTitle = itemView.findViewById<TextView>(R.id.title)
        val movieRatting = itemView.findViewById<TextView>(R.id.ratting)

        fun bind(movie: Movie) {
            Picasso.get().load(movie.picPath).into(movieImage, object: Callback {
                override fun onSuccess() {
                    Log.e("MovieDelegateAdapter", " Success")
                }

                override fun onError(e: Exception?) {
                    Log.e("MovieDelegateAdapter", e?.message + " path: $movie.picPath")
                }

            })
            movieDescription.text = movie.description
            movieRatting.text = movie.rating
            movieTitle.text = movie.name
        }
    }
}