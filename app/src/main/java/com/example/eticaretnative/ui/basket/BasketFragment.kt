package com.example.eticaretnative.ui.basket

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eticaretnative.adapters.BasketAdapter
import com.example.eticaretnative.base.BaseFragment
import com.example.eticaretnative.databinding.FragmentBasketBinding
import com.example.eticaretnative.model.ProductResponseModel
import com.example.eticaretnative.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment : BaseFragment<DetailViewModel, FragmentBasketBinding>(
    FragmentBasketBinding::inflate
) {
    private val basketAdapter: BasketAdapter = BasketAdapter()
    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()
    private var list= arrayListOf<ProductResponseModel>()

    override fun createFinished() {
        setRecycler()
        viewModel.getBasket()
        binding.button.setOnClickListener {
            if (list.isNotEmpty()){
                findNavController().navigate(BasketFragmentDirections.actionBasketFragmentToCardBottomFragment())
            }else{
                Toast.makeText(context,"Sepetiniz Boş",Toast.LENGTH_LONG).show()
            }
        }
        binding.buttonBasketNavigation.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            with(binding) {
                basketList.observe(viewLifecycleOwner) {
                    it?.let { it1 -> list.addAll(it1) }
                    basketAdapter.updateList(list)
                }

                error.observe(viewLifecycleOwner) {
                    if (it) {
                        textViewBasketError.visibility = View.VISIBLE
                        rvBasket.visibility = View.INVISIBLE
                    } else {
                        textViewBasketError.visibility = View.GONE
                        rvBasket.visibility = View.VISIBLE
                    }
                }

                totalPriceBasket.observe(viewLifecycleOwner){
                    it?.let {
                        textViewTotalPrice.text="$it $"
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        progressBasket.visibility=View.VISIBLE
                        rvBasket.visibility=View.INVISIBLE
                    }else{
                        progressBasket.visibility=View.GONE
                        rvBasket.visibility=View.VISIBLE
                    }
                }
            }

        }
    }

    private fun setRecycler() {
        with(binding.rvBasket) {
            layoutManager = LinearLayoutManager(context)
            adapter = basketAdapter
            setHasFixedSize(true)
        }
    }

}