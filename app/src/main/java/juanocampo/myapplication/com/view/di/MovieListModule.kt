package juanocampo.myapplication.com.view.di

import dagger.Module
import dagger.Provides
import juanocampo.myapplication.com.domain.IPaging
import juanocampo.myapplication.com.viewmodel.MovieViewModelFactory

@Module
class MovieListModule {

    @Provides
    @MovieListScope
    fun providesMoviewViewModelFactory(paging: IPaging) = MovieViewModelFactory(paging)

}