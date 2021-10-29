package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.data.remote.NetworkModule
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.presentation.details.DetailsDialog.Companion.PIZZA_PASSED_ID_KEY
import com.example.margarettipizza.utils.hideKeyboard
import com.example.margarettipizza.views.MarginItemDecoration
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
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
                        query?.let { query ->
                            viewModel.filterByName(query)
                        }
                        val qwe = viewModel.filtredList?.also {
                            Log.d(
                                "hash",
                                "rxGetByName: ${it.hashCode()} "
                            )
                        }?.doOnSubscribe {
                            Log.d("qwe", "onViewCreated: subed 1")
                        }?.doOnEach {
                            Log.d("qwe", "onViewCreated:2 ${it.value} ")
                        }?.subscribe {
                            Log.d("qwe", "onViewCreated:$it ")
                        }
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
            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }.observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                pizzaListAdapter.submitList(it)
            }, {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        )

        val qwe = NetworkModule.retrofit
        view.postDelayed({
            qwe.getAllPizza().subscribeOn(Schedulers.io()).subscribe()
        }, 3000)

//        viewModel.pizzaList.observe(viewLifecycleOwner) { pizzaList ->
//            pizzaListAdapter.submitList(pizzaList)
//        }

        disposable.add(list)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }


}