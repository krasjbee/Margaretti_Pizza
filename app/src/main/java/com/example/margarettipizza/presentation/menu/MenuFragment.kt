package com.example.margarettipizza.presentation.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.data.repository.PizzaRepository
import com.example.margarettipizza.data.repository.PizzaRepositoryImpl
import com.example.margarettipizza.databinding.FragmentHomeBinding
import com.example.margarettipizza.presentation.details.DetailsDialog
import com.example.margarettipizza.views.MarginItemDecoration

class MenuFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pizzaListAdapter = PizzaListAdapter {
            Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
            // FIXME: 19.10.2021 delete
            val dets = DetailsDialog()
            dets.show(parentFragmentManager, "qwe")
        }
        with(binding) {
            rvPizzaList.apply {
                adapter = pizzaListAdapter
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                addItemDecoration(MarginItemDecoration(requireContext(), 24))
            }
        }
        // FIXME: 19.10.2021 delete , used for test
        val repo: PizzaRepository = PizzaRepositoryImpl()
        val list = repo.getAll()
        pizzaListAdapter.submitList(list)
        super.onViewCreated(view, savedInstanceState)
    }
}