package com.example.view.menu

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.view.R
import com.example.view.cart.CartFragment
import com.example.view.databinding.FragmentHomeBinding
import com.example.view.details.DetailsDialog
import com.example.view.details.DetailsDialog.Companion.PIZZA_PASSED_ID_KEY
import com.example.view.utils.hideKeyboard
import com.example.view.views.MarginItemDecoration
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

//fixme cleanup
class MenuFragment : DaggerFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var viewModel: MenuViewModel
    private val disposable = CompositeDisposable()
    private lateinit var pizzaListAdapter: PizzaListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Creating rv adapter
        pizzaListAdapter = PizzaListAdapter { selectedPizza ->
            val dets = DetailsDialog()
            val bundle = bundleOf(PIZZA_PASSED_ID_KEY to selectedPizza.id)
            dets.arguments = bundle
            dets.show(parentFragmentManager, null)
        }

        //subscribe to pizza list
        subscribeToList()
        //setting up bottom bar
        setupBottomBar()
        //Setting up rv
        setupRecyclerView()
        //Setting up searchview query handling
        setupSearchView()
        //shows initial list
        viewModel.getPizzaList()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeToList() {
        viewModel.pizzaList.observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
            showLoad(true)
        }.subscribe({ pizzaList ->
            showLoad(false)
            pizzaListAdapter.submitList(pizzaList)
        }, {
            showLoad(false)
            Snackbar.make(requireView(), "An error occurred", Snackbar.LENGTH_SHORT)
//                .setBackgroundTint(resources.getColor(R.color.main_theme_color,null))
//                .setTextColor(resources.getColor(R.color.text_color_black,null))
                .show()
        })
    }

    private fun setupBottomBar() {
        disposable.add(
            viewModel.getOrderNetworth().observeOn(AndroidSchedulers.mainThread())
                .subscribe { setBottomBarPrice(it) })
        binding.llClickable.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.main_container,
                    CartFragment()
                )
                addToBackStack(null)
            }
        }
    }

    private fun setBottomBarPrice(price: Int) {
        binding.grBottombar.isGone = price == 0
        binding.tvPizzaPrice.text = String.format(
            requireActivity().getString(R.string.ruble_symbol),
            price
        )
    }


    override fun onDestroyView() {
        super.onDestroyView()
        disposable.clear()
    }

    private fun showLoad(isLoad: Boolean) {
        binding.groupMenuContent.isVisible = !isLoad
        binding.piLoading.isVisible = isLoad
    }

    private fun setupSearchView() {
        binding.svPizzaFilter.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { query ->
                        viewModel.getFilteredList(query)
                    }
                    hideKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    //do nothing
                    return true
                }
            }
            )
            setOnSearchClickListener {
                background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.search_view_background,
                    null
                )
            }
//                Setting up searchview background if it's closed
            setOnCloseListener {
                background = ResourcesCompat.getDrawable(resources, R.color.white, null)
                viewModel.getPizzaList()
                false
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvPizzaList.apply {
            adapter = pizzaListAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(requireContext(), 25))
        }
    }

}