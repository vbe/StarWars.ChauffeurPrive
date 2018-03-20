package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Vincent on 3/19/2018.
 */

abstract class SingleDataViewModelWithParams<in P, D> : ViewModel() {
    abstract fun getData(params: P? = null, forceReload: Boolean = false): LiveData<DataResult<D>>
}

abstract class SingleDataViewModel<D> : SingleDataViewModelWithParams<Any?, D>() {
    override fun getData(params: Any?, forceReload: Boolean) = getData(forceReload)
    abstract fun getData(forceReload: Boolean = false): LiveData<DataResult<D>>
}