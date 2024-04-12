package com.example.randomimageapp.viewmodel

import android.text.Spannable.Factory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.randomimageapp.data.NetworkPhotoRepository
import com.example.randomimageapp.data.PhotoRepository
import com.example.randomimageapp.model.MarsPhoto
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NLUiState{
    data class Success(val photos:List<MarsPhoto>):NLUiState
    object Error:NLUiState
    object Loading:NLUiState
}

class NLViewModel(private val photoRepository: PhotoRepository):ViewModel() {
    var nlUiState:NLUiState by mutableStateOf(NLUiState.Loading)
        private set

    init {
        getNLPhotos()
    }

    private fun getNLPhotos() {
        viewModelScope.launch {
            nlUiState = try{
                val listResult = photoRepository.getPhotos()
                NLUiState.Success(listResult)
            }catch (e:IOException){
                NLUiState.Error
            }

        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application=(this[APPLICATION_KEY] as MarsPhotoApplication)
                val photoRepository = application.container.photoRepository
                NLViewModel(photoRepository=photoRepository)
            }
        }
    }
}