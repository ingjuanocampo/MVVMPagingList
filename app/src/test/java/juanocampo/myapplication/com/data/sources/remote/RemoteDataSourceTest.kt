package juanocampo.myapplication.com.data.sources.remote

import juanocampo.myapplication.com.data.sources.remote.RemoteDataSource.Companion.API_KEY
import juanocampo.myapplication.com.data.sources.remote.RemoteDataSource.Companion.LANGUAGE
import juanocampo.myapplication.com.data.sources.remote.domain.MovieResponse
import juanocampo.myapplication.com.data.sources.remote.domain.MoviesDBResponse
import juanocampo.myapplication.com.data.sources.remote.service.MovieDBApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import org.mockito.Mockito.`when` as whenever


class RemoteDataSourceTest {

    @Mock
    lateinit var api: MovieDBApi
    @Mock
    lateinit var request: Call<MoviesDBResponse>
    @Mock
    lateinit var response: Response<MoviesDBResponse>
    @Mock
    lateinit var movieResponse: MoviesDBResponse
    @Mock
    lateinit var listMovies: List<MovieResponse>

    lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        whenever(api.fetchMovies(language = LANGUAGE, key = API_KEY, page = 0)).thenReturn(request)
        remoteDataSource = RemoteDataSource(api)

    }

    @Test
    fun `fetchMovies with a unsuccessfully response will return an empty list`() {
        whenever(request.execute()).thenReturn(response)
        whenever(response.isSuccessful).thenReturn(false)

        val response = remoteDataSource.fetchMovies(0)
        verify(api).fetchMovies(language = LANGUAGE, key = API_KEY, page = 0)
        assertNotNull(response)
        assertTrue(response.isEmpty())
    }

    @Test
    fun `fetchMovies with a successfully response and non null body will return a result`() {
        whenever(request.execute()).thenReturn(response)
        whenever(response.isSuccessful).thenReturn(true)
        whenever(response.body()).thenReturn(movieResponse)
        whenever(movieResponse.results).thenReturn(listMovies)

        val response = remoteDataSource.fetchMovies(0)
        verify(api).fetchMovies(language = LANGUAGE, key = API_KEY, page = 0)
        assertNotNull(response)
        assertEquals(response, listMovies)
    }



    @Test
    fun `fetchMovies with a successfully response but null body will return an empty list`() {
        whenever(request.execute()).thenReturn(response)
        whenever(response.isSuccessful).thenReturn(true)
        whenever(response.body()).thenReturn(null)

        val response = remoteDataSource.fetchMovies(0)
        verify(api).fetchMovies(language = LANGUAGE, key = API_KEY, page = 0)
        assertNotNull(response)
        assertTrue(response.isEmpty())
    }

}