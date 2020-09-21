package com.movies.task.api


import com.movies.task.data.model.homeModel.Results
import com.movies.task.data.sources.remoteApi.ApiService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ArticlesServiceTest : BaseServiceTest<ApiService>() {

    private lateinit var service: ApiService
    private lateinit var results: Results

    @Before
    fun initService() {
        service = createService(ApiService::class.java)
    }

    @Throws(IOException::class)
    @Test
    fun testHomeAPI()   {
        enqueueResponse("/NowPlayingMovies.json")
        runBlocking {
            results = requireNotNull(service.getNowPlayingMovies().body()?.results?.get(0))
        }
        mockWebServer.takeRequest()

        assertThat(results.title,`is`("Mulan"))

    }


}
