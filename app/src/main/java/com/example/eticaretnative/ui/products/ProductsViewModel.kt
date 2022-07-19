package com.example.eticaretnative.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eticaretnative.model.ProductResponseModel
import com.example.eticaretnative.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor (private val productsRepository: ProductsRepository):ViewModel() {

    private val _productsData=MutableLiveData<List<ProductResponseModel>?>()
    val productsData:LiveData<List<ProductResponseModel>?>
        get() = _productsData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun getProductsData(category: String){
        _loading.value=true
        viewModelScope.launch {
            val request=productsRepository.getProducts(category)
            when(request){
                is NetworkResult.Success->{
                    _productsData.value=request.data
                    _loading.value=false
                }
                is NetworkResult.Error->{
                    _error.value=request.message
                    _loading.value=false
                }
            }
        }
    }
}