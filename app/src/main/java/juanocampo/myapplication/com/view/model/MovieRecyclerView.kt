package juanocampo.myapplication.com.view.model

import juanocampo.myapplication.com.utils.delegate.model.RecyclerViewType


data class MovieRecyclerView(
    val id: Int,
    val name: String = "",
    val rating: String = "",
    val picPath: String = "",
    val language: String = "",
    val description: String = ""
): RecyclerViewType {

    override fun getDelegateId() = id

    override fun getViewType() = MOVIE_ITEM.hashCode()

    companion object {
        const val MOVIE_ITEM = "movie_delegate_item"
        const val MOVIE_LOADER = "movie_delegate_item_loader"
    }

}