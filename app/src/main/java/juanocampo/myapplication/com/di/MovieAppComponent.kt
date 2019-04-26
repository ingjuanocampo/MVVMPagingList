package juanocampo.myapplication.com.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.di.RepositoryModule

@Component(modules = [RepositoryModule::class, ApplicationModule::class])
interface MovieAppComponent {

    fun provideApplication(): Application

    fun provideRepository(): IRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MovieAppComponent
    }}