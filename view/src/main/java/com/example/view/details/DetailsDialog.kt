package com.example.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.domain.entities.PizzaEntity
import com.example.view.R
import com.example.view.databinding.DialogDetailsBinding
import com.example.view.preview.PreviewFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class DetailsDialog : BottomSheetDialogFragment() {

    private val binding by viewBinding(DialogDetailsBinding::bind)

    @Inject
    lateinit var viewModel: DetailsViewModel

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = DialogDetailsBinding.inflate(inflater, container, false).root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val args = arguments ?: throw Exception("No element passed")
        val id = args.getInt(PIZZA_PASSED_ID_KEY)


        val pizzaStream = viewModel.getPizzaById(id)

        pizzaStream.observeOn(AndroidSchedulers.mainThread()).subscribe({ pizza ->

            setupViews(pizza)
            setupAddToCartButtonListener(pizza.id)
            setupOnPictureClickListener(pizza)

        }, {}, disposable)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupAddToCartButtonListener(id: Int) {
        binding.llClickable.setOnClickListener {
            dismiss()
            viewModel.addToCart(id)
        }
    }

    private fun setupViews(pizza: PizzaEntity) {
        Glide.with(this@DetailsDialog)
            .load(pizza.imageUrls.first())
            .into(binding.sivPizzaPic)
        binding.tvPizzaName.text = pizza.name
        binding.tvPizzaDescription.text = pizza.description
        binding.tvPizzaPrice.text = pizza.price
    }

    private fun setupOnPictureClickListener(pizza: PizzaEntity) {
        binding.sivPizzaPic.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.main_container,
                    PreviewFragment.newInstance(pizza.id)
                )
                addToBackStack(null)
            }
            //hide this dialog after navigate to other screen
            dismiss()
        }
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }

    companion object {
        const val PIZZA_PASSED_ID_KEY = "PizzaPassedId"
    }
}