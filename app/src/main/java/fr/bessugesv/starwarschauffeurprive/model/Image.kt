package fr.bessugesv.starwarschauffeurprive.model

/**
 * Created by Vincent on 3/19/2018.
 */
class Image(val baseUrl: String, val path: String) {
    val url by lazy {
        baseUrl + path
    }
}