package juanocampo.myapplication.com.di

import juanocampo.myapplication.com.MovieApp
import juanocampo.myapplication.com.view.di.DaggerMovieViewComponent
import juanocampo.myapplication.com.view.fragment.MovieListFragment

class AndroidInjectorUtils {

    companion object {
        @JvmStatic
        fun inject(fragment: MovieListFragment) {
            DaggerMovieViewComponent
                .builder()
                .movieAppComponent(MovieApp.instance?.component)
                .build().inject(fragment)
        }
    }

}