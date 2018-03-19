package fr.bessugesv.starwarschauffeurprive

import com.squareup.okhttp.internal.io.FileSystem
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import okio.Buffer
import java.io.File
import java.io.InputStream

/**
 * Created by Vincent on 3/19/2018.
 */
object TestHelpers {

    fun enqueueMockFile(server: MockWebServer, file: File) {
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            body = Buffer().apply { writeAll(FileSystem.SYSTEM.source(file)) }
        })
    }

    fun enqueueMockData(server: MockWebServer, input: InputStream) {
        server.enqueue(MockResponse().apply {
            setResponseCode(200)
            body = Buffer().apply { this.readFrom(input) }
        })
    }
}