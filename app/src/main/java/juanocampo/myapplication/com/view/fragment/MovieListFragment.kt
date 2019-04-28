package juanocampo.myapplication.com.view.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import juanocampo.myapplication.com.R
import juanocampo.myapplication.com.di.AndroidInjectorUtils
import juanocampo.myapplication.com.view.adapter.MovieListAdapter
import juanocampo.myapplication.com.viewmodel.MovieViewModel
import juanocampo.myapplication.com.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.movie_list_fragment.*
import javax.inject.Inject

class MovieListFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: MovieViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    private lateinit var adapter: MovieListAdapter

    private lateinit var manager: LinearLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidInjectorUtils.inject(this)
        manager = LinearLayoutManager(context)
        movieList.layoutManager = manager
        adapter = MovieListAdapter()
        movieList.adapter = adapter

        var viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MovieViewModel::class.java)
        viewModel.fetchMoviesByPage()

        viewModel.movieListLiveData.observe(this, Observer {
            it?.let { items ->
                adapter.addItems(items)
            }
        })

        viewModel.errorLiveData.observe(this, Observer {
            it?.let { error ->
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        })

        movieList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    if (manager.findLastVisibleItemPosition() >= adapter.items.size - 4) {
                        viewModel.fetchMoviesByPage()
                    }
                }
            }

        })

    }
}