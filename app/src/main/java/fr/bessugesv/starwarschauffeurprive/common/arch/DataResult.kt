package fr.bessugesv.starwarschauffeurprive.common.arch

/**
 * Created by Vincent on 3/19/2018.
 */
sealed class DataResult<D>

class SUCCESS<D>(val data: D) : DataResult<D>()
class LOADING<D> : DataResult<D>()
class ERROR<D> : DataResult<D>()

