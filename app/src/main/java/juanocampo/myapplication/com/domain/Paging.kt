package juanocampo.myapplication.com.domain

import juanocampo.myapplication.com.data.IRepository
import juanocampo.myapplication.com.data.domain.RepositoryStates
import juanocampo.myapplication.com.domain.domain.PagingStates
import juanocampo.myapplication.com.domain.mapper.DomainMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Paging(
    private val iRepository: IRepository,
    private val domainMapper: DomainMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IPaging {

    private var pageNumber = 1

    @Synchronized
    override suspend fun invoke(): PagingStates {
        return withContext(ioDispatcher) {
            when (val response = iRepository.requestMoviesByPage(pageNumber)) {
                is RepositoryStates.Success -> {
                    val mappedItems = domainMapper(response.items)
                    pageNumber++
                    PagingStates.SuccessPage(mappedItems, pageNumber)
                }
                is RepositoryStates.Error -> {
                    PagingStates.Error(response.error)
                }
            }
        }
    }
}