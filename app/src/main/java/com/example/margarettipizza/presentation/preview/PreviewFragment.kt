package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentPreviewBinding
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


//fixme set backarrow click
class PreviewFragment() : DaggerFragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)

    @Inject
    lateinit var viewModel: PreviewViewModel
    private var pageChangeCallback: ViewPager2.OnPageChangeCallback? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pizzaId =
            arguments?.getInt(PREVIEW_PIZZA_ID) ?: throw Exception("Element isn't passed")
        val pizzaStream = viewModel.getPizzaById(pizzaId)
        val viewPagerAdapter = ImageGalleryAdapter()



        pizzaStream.observeOn(AndroidSchedulers.mainThread()).subscribe({ pizza ->

            pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    //sets page counter to string like 1/4
                    val pageCounter = "${position + 1}/${pizza.imageUrls.size}"
                    binding.tvPageCounter.text = pageCounter
                    super.onPageSelected(position)
                }
            }
            //fixme fix warning
            with(binding) {
                tvTitle.text = pizza.name
                tvPageCounter.text = "1/${pizza.imageUrls}"
                //Setting up viewPager
                vpPizzaGallery.apply {
                    adapter = viewPagerAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    registerOnPageChangeCallback(pageChangeCallback!!)
                }
            }
            viewPagerAdapter.setList(pizza.imageUrls)
        }, {})



        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        pageChangeCallback?.let { binding.vpPizzaGallery.unregisterOnPageChangeCallback(it) }
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance(pizzaId: Int): PreviewFragment {
            val args = bundleOf(PREVIEW_PIZZA_ID to pizzaId)
            val fragment = PreviewFragment()
            fragment.arguments = args
            return fragment
        }

        const val PREVIEW_PIZZA_ID = "PREVIEW_PIZZA_ID"
    }
}