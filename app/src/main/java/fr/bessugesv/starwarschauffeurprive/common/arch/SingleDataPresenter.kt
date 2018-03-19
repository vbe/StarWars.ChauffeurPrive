package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.view.View
import fr.bessugesv.starwarschauffeurprive.databinding.ViewErrorBinding

/**
 * Created by Vincent on 3/19/2018.
 */
open class SingleDataPresenterWithParams<in P, D>(
        val owner: LifecycleOwner,
        val model: SingleDataViewModelWithParams<P, D>,
        val loadingView: View,
        val errorView: ViewErrorBinding,
        val onSuccess: (D) -> Unit) {

    private var params: P? = null

    init {
        errorView.btnReload.setOnClickListener {
            load(params)
        }
    }

    fun load(params: P? = null) {
        this.params = params
        model.getData(params).observe(owner, Observer {
            when (it) {
                is LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                }
                is SUCCESS -> {
                    loadingView.visibility = View.GONE
                    errorView.root.visibility = View.GONE
                    onSuccess(it.data)
                }
                is ERROR -> {
                    loadingView.visibility = View.GONE
                    errorView.root.visibility = View.VISIBLE
                }
            }
        })
    }
}

class SingleDataPresenter<D>(
        owner: LifecycleOwner,
        model: SingleDataViewModel<D>,
        loadingView: View,
        errorView: ViewErrorBinding,
        onSuccess: (D) -> Unit)
    : SingleDataPresenterWithParams<Any?, D>(
        owner, model, loadingView, errorView, onSuccess
)