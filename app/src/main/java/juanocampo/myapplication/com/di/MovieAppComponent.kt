package juanocampo.myapplication.com.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import juanocampo.myapplication.com.data.IRepository
import juanocampo.myapplication.com.data.di.RepositoryModule

@ApplicationScope
@Component(modules = [RepositoryModule::class, ApplicationModule::class])
interface MovieAppComponent {

    fun provideApplication(): Application

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MovieAppComponent
    }
}