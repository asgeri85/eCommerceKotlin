package com.example.eticaretnative.ui.basket

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eticaretnative.R
import com.example.eticaretnative.base.BaseFragment
import com.example.eticaretnative.databinding.FragmentHomeBinding
import com.example.eticaretnative.databinding.FragmentSuccessBinding
import com.example.eticaretnative.ui.detail.DetailViewModel
import com.example.eticaretnative.ui.home.HomeFragment

class SuccessFragment : BaseFragment<DetailViewModel,FragmentSuccessBinding>(
    FragmentSuccessBinding::inflate
) {
    override val viewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun createFinished() {
        binding.buttonSuccess.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment,false)
        }
    }

    override fun observeEvents() {

    }

}