package com.example.eticaretnative.ui.home

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eticaretnative.adapters.CategoryAdapter
import com.example.eticaretnative.base.BaseFragment
import com.example.eticaretnative.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel,FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {
    override val viewModel: HomeViewModel by viewModels<HomeViewModel>()
    private val categoryAdapter:CategoryAdapter= CategoryAdapter()

    override fun createFinished() {
        setRecyclerView()
        categoryAdapter.onClick={
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductsFragment(it))
        }
        binding.imageButtonBasketHome.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToBasketFragment())
        }
    }

    override fun observeEvents() {
        with(viewModel){
            with(binding){
                categoryData.observe(viewLifecycleOwner){
                    it?.let {
                        categoryAdapter.updateList(it)
                    }
                }

                loading.observe(viewLifecycleOwner){
                    if (it){
                        progressHome.visibility=View.VISIBLE
                        rvPopular.visibility=View.INVISIBLE
                    }else{
                        progressHome.visibility=View.GONE
                        rvPopular.visibility=View.VISIBLE
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
        with(binding.rvPopular){
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
            adapter=categoryAdapter
        }
    }

}