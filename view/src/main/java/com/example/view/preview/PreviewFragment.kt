package com.example.view.preview

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.commit
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.domain.entities.PizzaEntity
import com.example.view.R
import com.example.view.databinding.FragmentPreviewBinding
import com.example.view.details.DetailsDialog
import com.example.view.menu.MenuFragment
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class PreviewFragment : DaggerFragment(R.layout.fragment_preview) {

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
            setupViewPager(viewPagerAdapter, pizzaId)
            setupViews(pizza)
            binding.llClickable.setOnClickListener {
                viewModel.addToCart(pizzaId)
                parentFragmentManager.commit {
                    replace(R.id.main_container, MenuFragment::class.java, null, null)
                }
            }
            viewPagerAdapter.setList(pizza.imageUrls)
        }, {})



        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews(pizza: PizzaEntity) {
        binding.tvTitle.text = pizza.name
        val pageCounterText = "1/${pizza.imageUrls.size}"
        binding.tvPageCounter.text = pageCounterText
        binding.tvPizzaPrice.text = pizza.price
    }


    override fun onDestroyView() {
        disposable.clear()
        pageChangeCallback?.let { binding.vpPizzaGallery.unregisterOnPageChangeCallback(it) }
        super.onDestroyView()
    }

    private fun setupViewPager(
        viewPagerAdapter: ImageGalleryAdapter, pizzaId: Int
    ) {
        binding.vpPizzaGallery.apply {
            adapter = viewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(pageChangeCallback!!)
        }
        binding.ibBack.setOnClickListener {
            val dets = DetailsDialog()
            val bundle = bundleOf(DetailsDialog.PIZZA_PASSED_ID_KEY to pizzaId)
            dets.arguments = bundle
            dets.show(parentFragmentManager, null)
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