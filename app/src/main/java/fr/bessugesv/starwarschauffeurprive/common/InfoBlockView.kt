package fr.bessugesv.starwarschauffeurprive.common

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import fr.bessugesv.starwarschauffeurprive.R
import fr.bessugesv.starwarschauffeurprive.databinding.ViewInfoBlockBinding

/**
 * Created by Vincent on 3/18/2018.
 */
class InfoBlockView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var binding: ViewInfoBlockBinding

    init {
        orientation = VERTICAL
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_info_block, this, true)
    }

    var label: CharSequence? = null
        set(value) {
            field = value
            binding.textInfoLabel.text = value
        }

    var value: CharSequence? = null
        set(value) {
            field = value
            binding.textInfoValue.text = value
        }

    var details: CharSequence? = null
        set(value) {
            field = value
            binding.textInfoDetails.text = value
        }
}