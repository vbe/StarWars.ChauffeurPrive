package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Vincent on 3/19/2018.
 */
abstract class SingleDataViewModel<D> : ViewModel() {

    abstract fun getData(): LiveData<DataResult<D>>


}