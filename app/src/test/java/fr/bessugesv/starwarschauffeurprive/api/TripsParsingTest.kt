package fr.bessugesv.starwarschauffeurprive.api

import com.squareup.okhttp.internal.io.FileSystem
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import fr.bessugesv.starwarschauffeurprive.model.Trip
import okio.Buffer
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by Vincent on 3/17/2018.
 */
class TripsParsingTest {

    lateinit var trips: List<Trip>

    @Before
    fun setup() {
        val server = MockWebServer().also { it.start() }
        val service = StarWarsApi.getRetrofit(server.url("/").toString()).create(StarWarsService::class.java)
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            body = Buffer().apply { writeAll(FileSystem.SYSTEM.source(File("./mockdata/trips.json"))) }
        })
        trips = service.listTrips().execute().body() ?: emptyList()
    }

    @Test
    fun testDarkVador() {
        val darkVador = trips[0].pilot!!

        assertThat(darkVador.name, equalTo("Dark Vador"))
        assertThat(darkVador.avatarPath, equalTo("/static/dark-vador.png"))
        assertThat(darkVador.rating, equalTo(5.0f))
    }
}