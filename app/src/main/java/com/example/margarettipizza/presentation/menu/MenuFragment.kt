package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.views.MarginItemDecoration
import kotlin.system.exitProcess

class MenuFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.toolbar.inflateMenu(R.menu.top_app_bar)
//        val search= binding.toolbar.menu.findItem(R.id.search).actionView as SearchView
//        binding.searchQwe.setOnSearchClickListener {
//
//        }

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
                        query?.let { query ->
                            viewModel.filterByName(query)
                        }
                        // TODO: 21.10.2021 hide keyboard
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
                    binding.svPizzaFilter.isIconified = true
                } else {
                    exitProcess(0)
                }
            }
        }


        viewModel.pizzaList.observe(viewLifecycleOwner) { pizzaList ->
            pizzaListAdapter.submitList(pizzaList)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    //    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.top_app_bar,menu)
//        val menuItem = menu.findItem(R.id.search)
//        val searchView = menuItem.actionView as SearchView
//        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
//        super.onCreateOptionsMenu(menu, inflater)
//    }
    companion object {
        const val PIZZA_PASSED_ID_KEY = "PizzaPassedId"
    }

}