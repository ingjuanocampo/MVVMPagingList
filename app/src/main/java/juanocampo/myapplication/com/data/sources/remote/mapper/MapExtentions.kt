package juanocampo.myapplication.com.data.sources.remote.mapper

import juanocampo.myapplication.com.data.domain.Movie
import juanocampo.myapplication.com.data.sources.remote.domain.MovieResponse

fun MovieResponse.toMovie(imageBaseUrl: String) =
    Movie(
        id = this.id ?: 0,
        description = this.description ?: "",
        language = this.language ?: "",
        name = this.name ?: "",
        picPath = imageBaseUrl + this.picPath,
        rating = this.rating ?: ""
    )