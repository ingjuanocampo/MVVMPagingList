package juanocampo.myapplication.com.model.domain

data class Movie(
    val id: Int,
    val name: String = "",
    val rating: String = "",
    val picPath: String = "",
    val language: String = "",
    val description: String = ""
)