package juanocampo.myapplication.com.view.fragment

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AndroidInjectorUtils.inject(this)
        movieList.layoutManager = LinearLayoutManager(context)
        adapter = MovieListAdapter()
        movieList.adapter = adapter

        var viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MovieViewModel::class.java)


    }

}