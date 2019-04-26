package juanocampo.myapplication.com.model.sources.remote.mapper

import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse

fun MovieResponse.toMovie(imageBaseUrl: String) =
    Movie(
        id = this.id ?: 0,
        description = this.description ?: "",
        language = this.language ?: "",
        name = this.name ?: "",
        picPath = imageBaseUrl + this.picPath,
        rating = this.rating ?: ""
    )