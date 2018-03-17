package fr.bessugesv.starwarschauffeurprive.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vincent on 3/17/2018.
 */
object StarWarsApi {
    const val BASE_URL = "https://starwars.chauffeur-prive.com"

    object PATHS {
        const val TRIPS = "trips"
    }

    object Schema {
        object Trip {
            const val ID = "id"
            const val PILOT = "pilot"
            const val DISTANCE = "distance"
            const val DURATION = "duration"
            const val PICK_UP = "pick_up"
            const val DROP_OFF = "drop_off"

        }
        object Pilot {
            const val NAME = "name"
            const val AVATAR = "avatar"
            const val RATING = "rating"
        }
        object Distance {
            const val VALUE = "value"
            const val UNIT = "unit"
        }
        object Destination {
            const val NAME = "name"
            const val PICTURE = "picture"
            const val DATE = "date"
        }
    }


    fun getClient() = OkHttpClient.Builder()
            .addInterceptor({ chain ->
                val original = chain.request()

                val request = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()

                chain.proceed(request)
            })
            .build()



    fun getRetrofit() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = getRetrofit().create(StarWarsService::class.java)
}