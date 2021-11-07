package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentHomeBinding
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

        //fixme add handle error and loading normally
        val list = viewModel.pizzaList.doOnSubscribe {
            Log.d("onSubscribe", "onViewCreated: ")
            showLoad(true)
        }.observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                pizzaListAdapter.submitList(it)
                Log.d("qwe", "lsit: $it")
                showLoad(false)
            }, {
                Log.d("qwe", "list: error ${it.message} ")
                it.printStackTrace()
                Log.d("qwe", "onViewCreated: ${it.stackTraceToString()} ")
                showLoad(false)
            }, disposable
        )

//        requireActivity().onBackPressedDispatcher.addCallback {
//            if (!binding.svPizzaFilter.isIconified) {
//                binding.svPizzaFilter.onActionViewCollapsed()
//                binding.svPizzaFilter.background =
//                    ResourcesCompat.getDrawable(resources, R.color.white, null)
//            } else {
//                //fixme
//            }
//        }

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
                Log.d("qwe", "submitFilteredList: do on sub")
            }
            .subscribe(
                { filteredList ->
                    Log.d("qwe", "submitFilteredList: ")
                    Log.d("qwe", "submitFilteredList:$filteredList ")
                    showLoad(false)
                    pizzaListAdapter.submitList(filteredList)
                },
                {
                    showLoad(false)
                    Log.d("qwe", "submitFilteredList: error")
                },
                disposable
            )
    }


}