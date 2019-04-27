package juanocampo.myapplication.com.view.di

import dagger.Component
import juanocampo.myapplication.com.di.MovieAppComponent
import juanocampo.myapplication.com.view.fragment.MovieListFragment

@MovieListScope
@Component(dependencies = [MovieAppComponent::class], modules = [MovieListModule::class])
interface MovieViewComponent {

    fun inject(fragment: MovieListFragment)
}