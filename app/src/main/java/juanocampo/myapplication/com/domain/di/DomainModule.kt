package juanocampo.myapplication.com.domain.di

import dagger.Module
import dagger.Provides
import juanocampo.myapplication.com.data.IRepository
import juanocampo.myapplication.com.domain.IPaging
import juanocampo.myapplication.com.domain.Paging
import juanocampo.myapplication.com.domain.mapper.DomainMapper

@Module
class DomainModule {

    @Provides
    fun providesPagingUseCase(iRepository: IRepository, domainMapper: DomainMapper): IPaging {
        return Paging(iRepository = iRepository, domainMapper = domainMapper)
    }

    @Provides
    fun providesDomainMapper() = DomainMapper()

}