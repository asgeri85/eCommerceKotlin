package com.example.eticaretnative.ui.detail


import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.eticaretnative.base.BaseFragment
import com.example.eticaretnative.databinding.FragmentDetailBinding
import com.example.eticaretnative.model.ProductResponseModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel,FragmentDetailBinding>(
    FragmentDetailBinding::inflate
) {
    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private val args:DetailFragmentArgs by navArgs()
    private lateinit var product:ProductResponseModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.product?.let {
            product= it
        }
    }

    override fun createFinished() {
        with(binding){
            product=args.product
            binding.executePendingBindings()
            buttonNavigateDetail.setOnClickListener {
                findNavController().popBackStack()
            }
            buttonAddBasketDetail.setOnClickListener {
                viewModel.addBasket(product!!,1)
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToBasketFragment())
            }
        }

    }

    override fun observeEvents() {
    }

}