package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v4.widget.SwipeRefreshLayout
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
        val onSuccess: (D) -> Unit,
        val swipeRefresh: SwipeRefreshLayout? = null) {

    private var params: P? = null

    init {
        errorView.btnReload.setOnClickListener {
            load(params)
        }
        swipeRefresh?.setOnRefreshListener {
            load(params, true)
        }
    }

    fun load( params: P? = null, forceReload: Boolean = false) {
        this.params = params
        model.getData(params, forceReload).observe(owner, Observer {
            when (it) {
                is LOADING -> {
                    loadingView.visibility = View.VISIBLE
                    errorView.root.visibility = View.GONE
                }
                is SUCCESS -> {
                    loadingView.visibility = View.GONE
                    errorView.root.visibility = View.GONE
                    onSuccess(it.data)
                    swipeRefresh?.isRefreshing = false
                }
                is ERROR -> {
                    loadingView.visibility = View.GONE
                    errorView.root.visibility = View.VISIBLE
                    swipeRefresh?.isRefreshing = false
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
        onSuccess: (D) -> Unit,
        swipeRefresh: SwipeRefreshLayout? = null)
    : SingleDataPresenterWithParams<Any?, D>(
        owner, model, loadingView, errorView, onSuccess, swipeRefresh
)