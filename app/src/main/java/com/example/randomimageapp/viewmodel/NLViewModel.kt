package com.example.randomimageapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomimageapp.network.PexelApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NLUiState{
    data class Success(val photos:String):NLUiState
    object Error:NLUiState
    object Loading:NLUiState
}

class NLViewModel: ViewModel() {
    var nlUiState:NLUiState by mutableStateOf(NLUiState.Loading)
        private set

    init {
        getNLPhotos()
    }

    fun getNLPhotos() {
        viewModelScope.launch {
            nlUiState = try{
                val listResult = PexelApi.retrofitService.getPhotos("HMF6ecALkFNcpEXydSNSCUhFsMBl4XX8IqI20iwyvCcQMcWceUTjQbVJ","northern lights")
                NLUiState.Success(listResult)
            }catch (e:IOException){
                NLUiState.Error
            }

        }
    }
}