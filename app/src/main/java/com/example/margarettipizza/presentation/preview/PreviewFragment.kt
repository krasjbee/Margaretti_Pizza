package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentPreviewBinding

class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)
    private val viewModel by viewModels<PreviewViewModel>()

    private val pizzaName =
        arguments?.getString(PREVIEW_PIZZA_NAME_KEY) ?: throw Exception("Element isn't passed")
    private val pizzaImageUrls =
        arguments?.getStringArray(PREVIEW_URL_LIST_KEY) ?: throw Exception("Element isn't passed")

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //sets page counter to string like 1/4
            val pageCounter = "${position + 1}/${pizzaImageUrls.size}"
            binding.tvPageCounter.text = pageCounter
            super.onPageSelected(position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val viewPagerAdapter = ImageGalleryAdapter()

        with(binding) {
            tvTitle.text = pizzaName
            //Setting up viewPager
            vpPizzaGallery.apply {
                adapter = viewPagerAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                registerOnPageChangeCallback(pageChangeCallback)
            }
        }
        viewPagerAdapter.setList(pizzaImageUrls)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding.vpPizzaGallery.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(pizzaName: String, imageUrls: List<String>): PreviewFragment {
            val args = bundleOf(
                PREVIEW_PIZZA_NAME_KEY to pizzaName,
                PREVIEW_URL_LIST_KEY to imageUrls.toTypedArray()
            )
            val fragment = PreviewFragment()
            fragment.arguments = args
            return fragment
        }

        const val PREVIEW_PIZZA_NAME_KEY = "TO_PREVIEW_NAME_KEY"
        const val PREVIEW_URL_LIST_KEY = "PREVIEW_URL_LIST_KEY"
    }
}