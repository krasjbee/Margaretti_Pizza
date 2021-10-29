package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.data.remote.dto.PizzaDto
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.presentation.details.DetailsDialog.Companion.PIZZA_PASSED_ID_KEY
import com.example.margarettipizza.utils.hideKeyboard
import com.example.margarettipizza.views.MarginItemDecoration
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlin.system.exitProcess

class MenuFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<MenuViewModel>()
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //Creating rv adapter
        val pizzaListAdapter = PizzaListAdapter { selectedPizza ->
            val dets = DetailsDialog()
            val bundle = bundleOf(PIZZA_PASSED_ID_KEY to selectedPizza.id)
            dets.arguments = bundle
            dets.show(parentFragmentManager, null)
        }

        with(binding) {
            //Setting up rv
            rvPizzaList.apply {
                adapter = pizzaListAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                addItemDecoration(MarginItemDecoration(requireContext(), 25))
            }

            //Setting up searchview query handling
            svPizzaFilter.apply {
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val list = mutableListOf<PizzaDto>()
                        query?.let { query ->
                            viewModel.filterByName(query)
                        }
                        val filteredListStream = viewModel.filtredList?.doOnSubscribe {
                            showLoad(true)
                        }?.collectInto(list, { l, e ->
                            l.add(e)
                        })?.subscribeOn(AndroidSchedulers.mainThread())?.subscribe({
                            pizzaListAdapter.submitList(it)
                            showLoad(false)
                        }, { showLoad(false) }, disposable)
                        hideKeyboard()
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
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
            //idk how to do it properly, pls leave your feedback on it
            //if searchview query is not empty - clear it , else close app
            // TODO: 27.10.2021 clear search query?
            requireActivity().onBackPressedDispatcher.addCallback {
                if (!binding.svPizzaFilter.isIconified) {
                    binding.svPizzaFilter.onActionViewCollapsed()
                    binding.svPizzaFilter.background =
                        ResourcesCompat.getDrawable(resources, R.color.white, null)
                } else {
                    exitProcess(0)
                }
            }
        }

        val list = viewModel.pizzaList.doOnSubscribe {
            showLoad(true)
        }.observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                pizzaListAdapter.submitList(it)
                showLoad(false)
            }, {
                showLoad(false)
            }, disposable
        )

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }

    private fun showLoad(isLoad: Boolean) {
        binding.groupMenuContent.isVisible = !isLoad
        binding.piLoading.isVisible = isLoad
    }

}