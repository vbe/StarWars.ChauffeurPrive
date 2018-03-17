package fr.bessugesv.starwarschauffeurprive.model

/**
 * Created by Vincent on 3/17/2018.
 */
class Trip {
    var pilot: Pilot? = null
    var distance: Distance? = null
    var duration: Long? = null
    var pickUp: Place? = null
    var dropOff: Place? = null
}