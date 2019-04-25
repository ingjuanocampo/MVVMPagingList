package juanocampo.myapplication.com.model.sources.remote.service

import juanocampo.myapplication.com.model.domain.MovieResponse


interface IMovieService {
    fun fetchMovies(page: Int = 1): List<MovieResponse>
}