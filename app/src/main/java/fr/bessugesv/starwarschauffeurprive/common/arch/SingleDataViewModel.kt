package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Vincent on 3/19/2018.
 */

abstract class SingleDataViewModelWithParams<in P, D> : ViewModel() {
    abstract fun getData(params: P? = null): LiveData<DataResult<D>>
}

abstract class SingleDataViewModel<D> : SingleDataViewModelWithParams<Any?, D>() {
    override fun getData(params: Any?) = getData()
    abstract fun getData(): LiveData<DataResult<D>>
}