package com.example.margarettipizza.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.DialogDetailsBinding
import com.example.margarettipizza.presentation.preview.PreviewFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsDialog : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogDetailsBinding::bind)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        with(binding) {
            btToPreview.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main_container, PreviewFragment::class.java, null, null)
                    .addToBackStack(null)
                    .commit()
                //hide this dialog after navigate to other screen
                dismiss()
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

}