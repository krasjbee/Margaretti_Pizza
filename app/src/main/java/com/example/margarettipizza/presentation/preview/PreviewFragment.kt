package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentPreviewBinding

class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val viewModel by viewModels<PreviewViewModel>()
    private val binding by viewBinding(FragmentPreviewBinding::bind)
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //todo set list
            binding.tvPageCounter.text
            super.onPageSelected(position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewPagerAdapter = ImageGalleryAdapter()

        with(binding) {
            vpPizzaGallery.apply {
                adapter = viewPagerAdapter
                //todo set list
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                registerOnPageChangeCallback(pageChangeCallback)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding.vpPizzaGallery.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }
}