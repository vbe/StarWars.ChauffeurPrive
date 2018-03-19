package fr.bessugesv.starwarschauffeurprive.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import fr.bessugesv.starwarschauffeurprive.model.Image

/**
 * Created by Vincent on 3/19/2018.
 */
class ImageTypeAdapter(val baseUrl: String) : TypeAdapter<Image>() {
    override fun write(out: JsonWriter, value: Image) {
        out.value(value.path)
    }

    override fun read(input: JsonReader) = when (input.peek()) {
        JsonToken.NULL -> {
            input.nextNull()
            null
        }
        else -> Image(baseUrl, input.nextString())
    }
}