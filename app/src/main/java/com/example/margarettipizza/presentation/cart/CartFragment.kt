package com.example.margarettipizza.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.margarettipizza.R
import com.example.margarettipizza.databinding.FragmentCartBinding
import com.example.margarettipizza.presentation.menu.MenuFragment
import com.example.margarettipizza.views.MarginItemDecoration
import dagger.android.support.DaggerFragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class CartFragment : DaggerFragment(R.layout.fragment_cart) {


    private val binding by viewBinding(FragmentCartBinding::bind)
    private lateinit var cartAdapter: CartAdapter

    @Inject
    lateinit var viewModel: CartViewModel
    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupRecyclerView()
        setupViews()
        subscribeToOrder()
        subscribeToNetworth()


        super.onViewCreated(view, savedInstanceState)
    }

    private fun subscribeToNetworth() {
        disposable.add(
            viewModel.getNetworth().observeOn(AndroidSchedulers.mainThread())
                .subscribe { setBottomBarPrice(it) })
    }

    private fun subscribeToOrder() {
        disposable.add(
            viewModel.getOrderWithPizza().observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    cartAdapter.submitList(it)
                }, {

                })
        )
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }

    private fun setupAdapter() {
        cartAdapter = CartAdapter(onDecrementClick = {
            disposable.add(
                viewModel.decrementQuantity(it.orderEntity.pizzaId)
                    .subscribeOn(AndroidSchedulers.mainThread()).subscribe()
            )
        }, onIncrementClick = {
            disposable.add(
                viewModel.incrementQuantity(it.orderEntity.pizzaId)
                    .subscribeOn(AndroidSchedulers.mainThread()).subscribe()
            )
        })
    }

    private fun setBottomBarPrice(price: Int) {
        binding.bottmBar.isGone = price == 0
        binding.tvPizzaCartPrice.text = String.format(
            requireActivity().getString(R.string.ruble_symbol),
            price
        )
    }

    private fun setupRecyclerView() {
        binding.rvCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(requireContext(), 25))
            itemAnimator = null
        }
    }

    private fun setupViews() {
        binding.ibClearCart.setOnClickListener {
            disposable.add(
                viewModel.clearCart().subscribeOn(AndroidSchedulers.mainThread()).subscribe()
            )
            parentFragmentManager.commit {
                replace(
                    R.id.main_container,
                    MenuFragment()
                )
            }
        }
        binding.llClickable.setOnClickListener {

        }
    }
}