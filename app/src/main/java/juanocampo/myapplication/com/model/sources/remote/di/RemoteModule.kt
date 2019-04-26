package juanocampo.myapplication.com.model.sources.remote.di

import dagger.Module
import dagger.Provides
import juanocampo.myapplication.com.model.IRepository
import juanocampo.myapplication.com.model.Repository
import juanocampo.myapplication.com.model.sources.remote.IRemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.RemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.mapper.MovieMapper
import juanocampo.myapplication.com.model.sources.remote.service.MovieDBApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun providesGsorConverter() = GsonConverterFactory.create()

    @Singleton
    @Provides
    @Named("MovieDB_Base_URL")
    fun providesMovieDBBaseURL() = "https://api.themoviedb.org"

    @Singleton
    @Provides
    @Named("MovieDB_Image_URL")
    fun providesMovieDBImageURL() = "http://image.tmdb.org/t/p/w780//"

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit, baseUrl: String, converter: GsonConverterFactory): MovieDBApi {

        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)

        val retrofit = builder.build()
        return retrofit.create(MovieDBApi::class.java)
    }

    @Singleton
    @Provides
    fun providesRemoteDataSource(movieDBApi: MovieDBApi): IRemoteDataSource = RemoteDataSource(api = movieDBApi)

    @Singleton
    @Provides
    fun providesMovieMapper(@Named("MovieDB_Image_URL") baseImageUrl: String) = MovieMapper(baseImageUrl)

    @Singleton
    fun providesRepository(iRemoteDataSource: IRemoteDataSource, movieMapper: MovieMapper): IRepository =
        Repository(iRemoteDataSource, movieMapper)

}