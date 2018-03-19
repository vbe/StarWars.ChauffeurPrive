package fr.bessugesv.starwarschauffeurprive.api

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Vincent on 3/19/2018.
 */
class DateTypeAdapter : TypeAdapter<Date>() {

    override fun read(input: JsonReader) = when(input.peek()) {
        JsonToken.NULL -> {
            input.nextNull()
            null
        }
        else -> deserialize(input.nextString())
    }

    private fun deserialize(string: String) = FORMAT.parse(string)

    override fun write(out: JsonWriter, value: Date) {
        out.value(FORMAT.format(value))
    }

    companion object {
        val FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    }
}