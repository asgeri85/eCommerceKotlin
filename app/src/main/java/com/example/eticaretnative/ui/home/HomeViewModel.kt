package com.example.eticaretnative.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eticaretnative.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    private val _categoryData = MutableLiveData<List<String>?>()
    val categoryData: LiveData<List<String>?>
        get() = _categoryData

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    init {
        getData()
    }

    private fun getData() {
        _loading.value = true
        viewModelScope.launch {
            val request = homeRepository.getCategory()
            when (request) {
                is NetworkResult.Success -> {
                    _categoryData.value = request.data
                    _loading.value = false
                    Log.e("data",_categoryData.toString())
                }
                is NetworkResult.Error -> {
                    _error.value = request.message
                    _loading.value = false
                }
            }
        }
    }
}