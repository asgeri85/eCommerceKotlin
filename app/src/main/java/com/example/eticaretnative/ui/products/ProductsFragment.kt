package com.example.eticaretnative.ui.products

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eticaretnative.adapters.ProductAdapters
import com.example.eticaretnative.base.BaseFragment
import com.example.eticaretnative.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : BaseFragment<ProductsViewModel,FragmentProductsBinding>(
    FragmentProductsBinding::inflate
) {
    override val viewModel: ProductsViewModel by viewModels<ProductsViewModel>()
    private val args:ProductsFragmentArgs by navArgs()
    private val productAdapter:ProductAdapters= ProductAdapters()

    override fun createFinished() {
        setRecyclerView()
        viewModel.getProductsData(args.category!!)
        binding.buttonProductNavigate.setOnClickListener {
            findNavController().popBackStack()
        }
        productAdapter.onClick={
            findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToDetailFragment(it))
        }

    }

    override fun observeEvents() {
        with(viewModel){
            with(binding){
                productsData.observe(viewLifecycleOwner){
                    it?.let {
                        productAdapter.updateList(it)
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        rvProductsPopular.visibility=View.INVISIBLE
                        progressProducts.visibility=View.VISIBLE
                    }else{
                        rvProductsPopular.visibility=View.VISIBLE
                        progressProducts.visibility=View.GONE
                    }
                }

                error.observe(viewLifecycleOwner){
                    it?.let {
                        Toast.makeText(context,it,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun setRecyclerView(){
        binding.textProductTitle.text=args.category
        with(binding.rvProductsPopular){
            layoutManager=GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
            adapter=productAdapter
            setHasFixedSize(true)
        }
        with(binding.rvAllProducts){
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=productAdapter
            setHasFixedSize(true)
        }
    }

}