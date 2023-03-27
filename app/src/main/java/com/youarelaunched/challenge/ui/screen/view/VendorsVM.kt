package com.youarelaunched.challenge.ui.screen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youarelaunched.challenge.data.repository.VendorsRepository
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class VendorsVM @Inject constructor(
    private val repository: VendorsRepository
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val _uiState = MutableStateFlow(
        VendorsScreenUiState(
            vendors = null
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        getVendors()
        viewModelScope.launch {
            searchQuery
                .map { it.trim() }
                .filter { it.length >= MIN_LENGTH_FOR_SEARCH }
                .debounce(DEBOUNCE_TIME_MILLISECOND)
                .collectLatest { searchQuery ->
                    getVendors(searchQuery)
                }
        }
    }

    fun getVendors(searchQuery: String = "") {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    vendors = repository.getVendors(searchQuery)
                )
            }
        }
    }

    companion object {
        const val MIN_LENGTH_FOR_SEARCH = 3
        const val DEBOUNCE_TIME_MILLISECOND = 500L
    }

}