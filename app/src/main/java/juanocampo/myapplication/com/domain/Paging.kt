package juanocampo.myapplication.com.domain

import juanocampo.myapplication.com.data.IRepository
import juanocampo.myapplication.com.data.domain.Resource
import juanocampo.myapplication.com.data.domain.Status
import juanocampo.myapplication.com.view.model.MovieRecyclerView
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Paging(private val iRepository: IRepository,
             private val
             private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
             ): IPaging {

    private var pageNumber = 1

    @Synchronized
    override suspend fun invoke(): Resource<List<MovieRecyclerView>> {
        withContext(ioDispatcher) {


            val response = iRepository.requestMoviesByPage(pageNumber)


            when {
                response.status == Status.SUCCESS -> {
                    pageNumber++
                    handleSuccessCase(response)
                }
                else -> {
                    handleErrorCase(response)
                }
            }
        }
    }
}