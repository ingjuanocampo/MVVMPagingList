package juanocampo.myapplication.com.data.sources.remote.mapper

import juanocampo.myapplication.com.data.sources.remote.domain.MovieResponse
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import java.util.*

class MovieMapperTest {

    companion object {
        const val IMAGE_BASE_URL_MOCK = "image_url"
    }

    private val empytList = emptyList<MovieResponse>()
    private val mockList = ArrayList<MovieResponse>()
    private val mapper = MovieMapper(IMAGE_BASE_URL_MOCK)
    @Before
    fun setUp() {
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        mockList.add(createRandomMovie())
        val withNotName = MovieResponse(
            id = Random().nextInt(),
            name = null,
            rating = "rating ${Random().nextInt()})",
            language = "language ${Random().nextInt()})",
            description = "description ${Random().nextInt()})",
            picPath = "picPath ${Random().nextInt()})")

        mockList.add(withNotName)
        val withNotAverage = MovieResponse(
            id = Random().nextInt(),
            name = "name ${Random().nextInt()})",
            rating = null,
            language = "language ${Random().nextInt()})",
            description = "description ${Random().nextInt()})",
            picPath = "picPath ${Random().nextInt()})")

        mockList.add(withNotAverage)
    }

    private fun createRandomMovie() = MovieResponse(
        id = Random().nextInt(),
        name = "name ${Random().nextInt()})",
        rating = "rating ${Random().nextInt()})",
        language = "language ${Random().nextInt()})",
        description = "description ${Random().nextInt()})",
        picPath = "picPath ${Random().nextInt()})")


    @Test
    fun `parse empty list will return empty list`() {
        val response = mapper.mapResponseToAppModel(empytList)
        assertTrue(response.isEmpty())
    }


    @Test
    fun `parse random list will return a parsed list`() {
        val parsedItems = mapper.mapResponseToAppModel(mockList)
        assertEquals(mockList.size, parsedItems.size)

        val firstParseItem = parsedItems[0]
        val firstMockItem = mockList[0]

        assertEquals(firstMockItem.name, firstParseItem.name)
        assertEquals(firstMockItem.description, firstParseItem.description)
        assertEquals(firstMockItem.id, firstParseItem.id)
        assertEquals(firstMockItem.language, firstParseItem.language)
        assertEquals(IMAGE_BASE_URL_MOCK + firstMockItem.picPath, firstParseItem.picPath)
        assertEquals(firstMockItem.rating, firstParseItem.rating)
    }


}