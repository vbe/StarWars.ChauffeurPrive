package fr.bessugesv.starwarschauffeurprive.common.arch

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.view.View
import fr.bessugesv.starwarschauffeurprive.databinding.ViewErrorBinding

/**
 * Created by Vincent on 3/19/2018.
 */
class SingleDataPresenter<D>(
        val owner: LifecycleOwner,
        val model: SingleDataViewModel<D>,
        val loadingView: View,
        val errorView: ViewErrorBinding,
        val onSuccess: (D) -> Unit) {

    init {
        errorView.btnReload.setOnClickListener {
            load()
        }
    }

    fun load() {
        model.getData().observe(owner, Observer {
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