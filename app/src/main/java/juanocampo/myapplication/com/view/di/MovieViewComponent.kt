package juanocampo.myapplication.com.view.di

import dagger.Component

@Component(dependencies = [MovieViewComponent::class], modules = [MovieListModule::class])
interface MovieViewComponent {

    fun inject()

}