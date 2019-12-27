package app.krunal3kapadiya.mlglossary.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import app.krunal3kapadiya.mlglossary.R
import app.krunal3kapadiya.mlglossary.data.api.Mldefinitions
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : DialogFragment() {
    lateinit var mlDefinition: Mldefinitions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mlDefinition = it.getParcelable(ARG_DEFINITIONS)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.FilterDialogAnimation
        title.text = mlDefinition.name.toUpperCase()
        detail.text = mlDefinition.definition
    }

    companion object {
        @JvmStatic
        fun newInstance(mlDefinition: Mldefinitions) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DEFINITIONS, mlDefinition)

                }
            }

        private const val ARG_DEFINITIONS = "definitions"
    }
}
