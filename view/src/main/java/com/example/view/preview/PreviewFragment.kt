package com.example.margarettipizza.presentation.preview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentPreviewBinding
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject


//fixme set backarrow click
class PreviewFragment() : DaggerFragment(R.layout.fragment_preview) {

    private val binding by viewBinding(FragmentPreviewBinding::bind)

    @Inject
    lateinit var viewModel: PreviewViewModel
    private var pageChangeCallback: ViewPager2.OnPageChangeCallback? = null
    private val disposable = CompositeDisposable()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pizzaId =
            arguments?.getInt(PREVIEW_PIZZA_ID) ?: throw Exception("Element isn't passed")
        val pizzaStream = viewModel.getPizzaById(pizzaId)
        val viewPagerAdapter = ImageGalleryAdapter()


        pizzaStream.observeOn(AndroidSchedulers.mainThread()).subscribe({ pizza ->
            setupViewPagerCallback(pizza.imageUrls.size)
            setupViews(viewPagerAdapter)
            //fixme fix warning
            with(binding) {
                tvTitle.text = pizza.name
                val pageCounterText = "1/${pizza.imageUrls.size}"
                tvPageCounter.text = pageCounterText
                tvPizzaPrice.text = pizza.price
                llClickable.setOnClickListener {
                    disposable.addAll(viewModel.addToCard(pizzaId).subscribe())
                    parentFragmentManager.commit {
                        replace(R.id.main_container, MenuFragment::class.java, null, null)
                    }
                }
            }
            viewPagerAdapter.setList(pizza.imageUrls)
        }, {})



        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        disposable.clear()
        pageChangeCallback?.let { binding.vpPizzaGallery.unregisterOnPageChangeCallback(it) }
        super.onDestroyView()
    }

    private fun setupViews(
        viewPagerAdapter: ImageGalleryAdapter
    ) {
        binding.vpPizzaGallery.apply {
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(pageChangeCallback!!)
        }
        binding.ibBack.setOnClickListener {
            parentFragmentManager.popBackStackImmediate()
        }
    }

    private fun setupViewPagerCallback(listSize: Int) {
        pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                //sets page counter to string like 1/4
                val pageCounter = "${position + 1}/${listSize}"
                binding.tvPageCounter.text = pageCounter
                super.onPageSelected(position)
            }
        }
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