package juanocampo.myapplication.com.data.domain

data class Movie(
    val id: Int,
    val name: String = "",
    val rating: String = "",
    val picPath: String = "",
    val language: String = "",
    val description: String = ""
)