package juanocampo.myapplication.com.model

import juanocampo.myapplication.com.model.domain.Movie
import juanocampo.myapplication.com.model.domain.Status
import juanocampo.myapplication.com.model.sources.remote.IRemoteDataSource
import juanocampo.myapplication.com.model.sources.remote.domain.MovieResponse
import juanocampo.myapplication.com.model.sources.remote.mapper.MovieMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.io.IOException
import org.mockito.Mockito.`when` as whenever

class RepositoryTest {

    @Mock
    lateinit var iRemoteDataSource: IRemoteDataSource
    @Mock
    lateinit var iMapper: MovieMapper
    @Mock
    lateinit var listMoviesResponse: List<MovieResponse>
    @Mock
    lateinit var listMovies: List<Movie>

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = Repository(iRemoteDataSource, iMapper, Dispatchers.Unconfined)
    }

    @Test
    fun `requestMovies with empty service response will return an error`() = runBlocking {
        whenever(iRemoteDataSource.fetchMovies(0)).thenReturn(emptyList())
        val response = repository.requestMoviesByPage(0)
        verify(iRemoteDataSource).fetchMovies(0)
        assertTrue(response.status == Status.ERROR)
        assertTrue(response.message == "could not load info, try later")
    }

    @Test
    fun `requestMovies with valid service response will return a success data`() = runBlocking {
        whenever(iRemoteDataSource.fetchMovies(0)).thenReturn(listMoviesResponse)
        whenever(iMapper.mapResponseToAppModel(listMoviesResponse)).thenReturn(listMovies)
        val response = repository.requestMoviesByPage(0)
        verify(iRemoteDataSource).fetchMovies(0)
        verify(iMapper).mapResponseToAppModel(listMoviesResponse)
        assertTrue(response.status == Status.SUCCESS)
        assertEquals(response.info, listMovies)
    }

    @Test
    fun `requestMovies will throw an exception and will return an error`() = runBlocking {
        whenever(iRemoteDataSource.fetchMovies(0)).thenThrow(IOException())

        val response = repository.requestMoviesByPage(0)

        verify(iRemoteDataSource).fetchMovies(0)

        assertTrue(response.status == Status.ERROR)
        assertTrue(response.message == "Something went wrong")
    }
}