package com.example.eticaretnative.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eticaretnative.database.BaskeDAO
import com.example.eticaretnative.model.ProductResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val baskeDAO: BaskeDAO) : ViewModel() {

    private val _basketList = MutableLiveData<List<ProductResponseModel>?>()
    val basketList: LiveData<List<ProductResponseModel>?>
        get() = _basketList

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _totalPriceBasket=MutableLiveData<Double?>()
    val totalPriceBasket:LiveData<Double?>
        get() = _totalPriceBasket

    private val _orderStatus=MutableLiveData<Boolean>()
    val orderStatus:LiveData<Boolean>
        get() = _orderStatus

    fun addBasket(productResponseModel: ProductResponseModel, count: Int) {
        if (productResponseModel.count==0){
            productResponseModel.count+=1
            viewModelScope.launch(Dispatchers.IO){
                baskeDAO.insertBasket(productResponseModel)
            }
        }else {
            productResponseModel.count+=count
            viewModelScope.launch(Dispatchers.IO){
                baskeDAO.updateBasket(productResponseModel)
            }
        }
    }

    fun getBasket() {
        _loading.value = true
        var total=0.0
        viewModelScope.launch {
            val request = baskeDAO.readAllBasket()
            if (request.isNotEmpty()) {
                _basketList.value = request
                request.forEach { product ->
                    product.price?.let {
                        total+=it
                    }
                }
                _totalPriceBasket.value=total
                _loading.value = false
                _error.value=false
            } else {
                _error.value = true
                _loading.value = false
            }
        }
    }

    fun clearBasket(){
        viewModelScope.launch{
            try {
                baskeDAO.deleteBasket()
                _orderStatus.value=true
            }catch (e:Exception){
                _orderStatus.value=false
            }
        }
    }

}