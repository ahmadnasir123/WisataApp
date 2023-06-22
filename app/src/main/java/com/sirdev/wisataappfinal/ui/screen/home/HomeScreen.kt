package com.sirdev.wisataappfinal.ui.screen.home

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sirdev.wisataappfinal.data.Repository
import com.sirdev.wisataappfinal.model.OrderWisata
import com.sirdev.wisataappfinal.ui.common.UiState
import com.sirdev.wisataappfinal.ui.component.ScrollToTopButton
import com.sirdev.wisataappfinal.ui.component.WisataListItem
import com.sirdev.wisataappfinal.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Repository())),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getWisata()
            }
            is UiState.Success -> {
                HomeContent(
                    orderWisata = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                // Handle error state here
                val error = uiState.errorMessage
                // Display an error message or perform any other actions
                // based on the error state
            }
        }
    }
}

@Composable
fun HomeContent(
    orderWisata: List<OrderWisata>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {

    Box(modifier = modifier) {
        val scope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val showBtn: Boolean by remember {
            derivedStateOf { listState.firstVisibleItemIndex > 0 }
        }
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(orderWisata) { data ->
                WisataListItem(
                    id = data.wisata.wisataId,
                    name = data.wisata.name,
                    photoUrl = data.wisata.photo,
                    navigateToDetail = navigateToDetail
                )
            }
        }
        AnimatedVisibility(
            visible = showBtn,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .padding(bottom = 30.dp, end = 30.dp)
                .align(Alignment.BottomEnd)
        ) {
            ScrollToTopButton(
                onClick = {
                    scope.launch{
                        listState.scrollToItem(index = 0)
                    }
                }
            )
        }
    }
}