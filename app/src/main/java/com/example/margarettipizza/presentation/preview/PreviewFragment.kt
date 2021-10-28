package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.databinding.FragmentPreviewBinding

class PreviewFragment : Fragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)
    private val viewModel by viewModels<PreviewViewModel>()
    private val pizzaId =
        arguments?.getInt(PREVIEW_PIZZA_ID) ?: throw Exception("Element isn't passed")
    private var pizza: PizzaDto? = null

    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //sets page counter to string like 1/4
            val pageCounter = "${position + 1}/${pizza?.imageUrls?.size}"
            binding.tvPageCounter.text = pageCounter
            super.onPageSelected(position)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pizza = viewModel.getPizzaById(pizzaId)
        val viewPagerAdapter = ImageGalleryAdapter()

        with(binding) {
            tvTitle.text = pizza?.name
            //Setting up viewPager
            vpPizzaGallery.apply {
                adapter = viewPagerAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                registerOnPageChangeCallback(pageChangeCallback)
            }
        }
        pizza?.imageUrls?.let { viewPagerAdapter.setList(it) }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        binding.vpPizzaGallery.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(pizzaId: Int): PreviewFragment {
            val args = bundleOf()
            val fragment = PreviewFragment()
            fragment.arguments = args
            return fragment
        }

        const val PREVIEW_PIZZA_ID = "PREVIEW_PIZZA_ID"
    }
}