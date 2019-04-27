package juanocampo.myapplication.com

import android.app.Application
import juanocampo.myapplication.com.di.DaggerMovieAppComponent
import juanocampo.myapplication.com.di.MovieAppComponent

class MovieApp: Application() {

    companion object {
        var instance: MovieApp? = null
    }

    lateinit var component: MovieAppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerMovieAppComponent.builder().application(this).build()

    }

}