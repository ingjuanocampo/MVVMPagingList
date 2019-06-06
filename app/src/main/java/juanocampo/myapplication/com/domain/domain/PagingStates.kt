package juanocampo.myapplication.com.domain.domain

import juanocampo.myapplication.com.view.model.MovieRecyclerView

sealed class PagingStates {
    data class Error(val error: String): PagingStates()
    data class SuccessPage(val newItems: List<MovieRecyclerView>, val lastPaged: Int): PagingStates()
}