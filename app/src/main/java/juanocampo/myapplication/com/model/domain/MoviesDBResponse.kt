package juanocampo.myapplication.com.model.domain

import com.google.gson.annotations.SerializedName

data class MoviesDBResponse(val page: Int?,
                            @SerializedName("total_results") val totalResults: String?,
                            @SerializedName("total_pages") val totalPages: String?,
                            val results: List<MovieResponse>)

data class MovieResponse(val id: Int?,
                         @SerializedName("original_name") val name: String?,
                         @SerializedName("vote_average") val rating: String?,
                         @SerializedName("poster_path") val picPath: String?,
                         @SerializedName("original_language") val language: String?,
                         @SerializedName("overview") val description: String?)