package com.sirdev.wisataappfinal.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sirdev.wisataappfinal.data.Repository
import com.sirdev.wisataappfinal.model.OrderWisata
import com.sirdev.wisataappfinal.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderWisata>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderWisata>>> get() = _uiState

    fun getWisata() {
        viewModelScope.launch {
            repository.getWisata()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { wisatan ->
                    _uiState.value = UiState.Success(wisatan)
                }
        }
    }
}