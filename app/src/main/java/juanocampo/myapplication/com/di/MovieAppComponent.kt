package juanocampo.myapplication.com.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import juanocampo.myapplication.com.domain.IPaging
import juanocampo.myapplication.com.domain.di.DomainModule

@ApplicationScope
@Component(modules = [ApplicationModule::class, DomainModule::class])
interface MovieAppComponent {

    fun provideApplication(): Application

    fun providesPaging(): IPaging

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): MovieAppComponent
    }
}