package juanocampo.myapplication.com.domain

import juanocampo.myapplication.com.data.domain.Resource
import juanocampo.myapplication.com.view.model.MovieRecyclerView

interface IPaging {

    suspend operator fun invoke(): Resource<List<MovieRecyclerView>>
}