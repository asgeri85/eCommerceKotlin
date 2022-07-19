package com.example.eticaretnative.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.eticaretnative.databinding.FragmentCardBottomBinding
import com.example.eticaretnative.ui.detail.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardBottomFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCardBottomBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvents()
        binding.bottomButton.setOnClickListener {
            viewModel.clearBasket()
        }
    }

    private fun observeEvents() {
        with(viewModel) {
            orderStatus.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(CardBottomFragmentDirections.actionCardBottomFragmentToSuccessFragment())
                }else{
                    Toast.makeText(context,"Bİlinmeyen bir hata oluştu",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}