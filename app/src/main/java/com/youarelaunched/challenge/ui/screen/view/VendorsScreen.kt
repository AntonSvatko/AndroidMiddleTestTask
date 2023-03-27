package com.youarelaunched.challenge.ui.screen.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.components.ChatsumerSnackbar
import com.youarelaunched.challenge.ui.screen.view.components.SearchView
import com.youarelaunched.challenge.ui.screen.view.components.VendorItem
import com.youarelaunched.challenge.ui.theme.VendorAppTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VendorsRoute(
    viewModel: VendorsVM
) {
    val uiState by viewModel.uiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    VendorsScreen(
        uiState = uiState,
        onSearchQuery = {

        }, onSearchClick = {
            keyboardController?.hide()
        }
    )
}

@Composable
fun VendorsScreen(
    uiState: VendorsScreenUiState,
    onSearchQuery: (String) -> Unit,
    onSearchClick: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = VendorAppTheme.colors.background,
        snackbarHost = { ChatsumerSnackbar(it) }
    ) { paddings ->
        Column {
            SearchView(onSearchQuery, onSearchClick)
            if (!uiState.vendors.isNullOrEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddings)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(
                        vertical = 24.dp,
                        horizontal = 16.dp
                    )
                ) {
                    items(uiState.vendors) { vendor ->
                        VendorItem(
                            vendor = vendor
                        )
                    }
                }
            }
        }
    }
}