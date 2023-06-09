package com.sirdev.wisataappfinal.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirdev.wisataappfinal.data.Repository
import com.sirdev.wisataappfinal.model.OrderWisata
import com.sirdev.wisataappfinal.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderWisata>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderWisata>> get() = _uiState

    fun getWisataId(wisataId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderById(wisataId))
        }
    }
}