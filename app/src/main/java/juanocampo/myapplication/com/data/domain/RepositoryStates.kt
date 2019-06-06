package juanocampo.myapplication.com.data.domain

sealed class RepositoryStates {
    data class Error(val error: String): RepositoryStates()
    data class Success(val items: List<Movie>): RepositoryStates()
}