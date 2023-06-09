package com.sirdev.wisataappfinal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sirdev.wisataappfinal.data.Repository
import com.sirdev.wisataappfinal.ui.screen.detail.DetailViewModel
import com.sirdev.wisataappfinal.ui.screen.home.HomeViewModel

class ViewModelFactory (
    private val repository: Repository
): ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
    }
}