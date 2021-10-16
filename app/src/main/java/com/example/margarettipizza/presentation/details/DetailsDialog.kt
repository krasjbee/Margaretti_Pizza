package com.example.margarettipizza.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.margarettipizza.R
import com.example.margarettipizza.presentation.preview.PreviewFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailsDialog : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btToPreview: Button = view.findViewById(R.id.bt_to_preview)
        btToPreview.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_container, PreviewFragment::class.java, null, null)
                .addToBackStack(null)
                .commit()
            //hide this dialog after navigate to other screen
            dismiss()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}