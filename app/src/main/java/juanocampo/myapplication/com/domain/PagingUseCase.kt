package juanocampo.myapplication.com.domain

import juanocampo.myapplication.com.data.domain.Resource
import juanocampo.myapplication.com.view.model.MovieRecyclerView

interface PagingUseCase {

    suspend fun getItemsByPage(): Resource<List<MovieRecyclerView>>

}