package juanocampo.myapplication.com.domain

import juanocampo.myapplication.com.domain.domain.PagingStates

interface IPaging {

    suspend operator fun invoke(): PagingStates
}