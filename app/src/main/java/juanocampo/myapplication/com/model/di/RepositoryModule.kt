package juanocampo.myapplication.com.model.di

import dagger.Module
import dagger.Provides
import juanocampo.myapplication.com.di.ApplicationScope
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.Repository
import juanocampo.myapplication.com.model.sources.remote.IRemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.RemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.mapper.MovieMapper
import juanocampo.myapplication.com.model.sources.remote.service.MovieDBApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun providesApi(): MovieDBApi {

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit = builder.build()
        return retrofit.create(MovieDBApi::class.java)
    }

    @ApplicationScope
    @Provides
    fun providesRemoteDataSource(movieDBApi: MovieDBApi): IRemoteDataSource = RemoteDataSource(api = movieDBApi)

    @ApplicationScope
    @Provides
    fun providesMovieMapper() = MovieMapper( "http://image.tmdb.org/t/p/w185//")

    @Provides
    fun providesRepository(iRemoteDataSource: IRemoteDataSource, movieMapper: MovieMapper): IRepository =
        Repository(iRemoteDataSource, movieMapper)

}