package com.example.margarettipizza.presentation.menu

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
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.cart.CartFragment
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.presentation.details.DetailsDialog.Companion.PIZZA_PASSED_ID_KEY
import com.example.margarettipizza.utils.hideKeyboard
import com.example.margarettipizza.views.MarginItemDecoration
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
        //Setting up rv
        setupRecyclerView()
        //Setting up searchview query handling
        setupSearchView()
        //shows initial list
        showUnfilteredList()
        setupViews()

        disposable.add(
            viewModel.getOrderNetworth().observeOn(AndroidSchedulers.mainThread())
                .subscribe { setBottomBarPrice(it) })

        //fixme
//        requireActivity().onBackPressedDispatcher.addCallback {
//            if (!binding.svPizzaFilter.isIconified) {
//                binding.svPizzaFilter.onActionViewCollapsed()
//                binding.svPizzaFilter.background =
//                    ResourcesCompat.getDrawable(resources, R.color.white, null)
//            } else {
//                if (parentFragmentManager.backStackEntryCount>0){
//                    Log.d("qwe", "onViewCreated: popped ${parentFragmentManager.backStackEntryCount}")
//                    parentFragmentManager.popBackStackImmediate()
//                    Log.d("qwe", "onViewCreated: popped ")
//                } else {
//                    Log.d("qwe", "onViewCreated: exit ")
//                    exitProcess(0)
//                }
//            }
//        }


        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupViews() {
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

    private fun showUnfilteredList() {
        viewModel.pizzaList.doOnSubscribe {
            showLoad(true)
        }.observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                pizzaListAdapter.submitList(it)
                showLoad(false)
            }, {
                showLoad(false)
            }, disposable
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
                        submitFilteredList(query)
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
                showUnfilteredList()
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

    private fun submitFilteredList(query: String) {
        viewModel.getFilteredList(query)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showLoad(true)
            }
            .subscribe(
                { filteredList ->
                    showLoad(false)
                    pizzaListAdapter.submitList(filteredList)
                },
                {
                    showLoad(false)
                },
                disposable
            )
    }


}