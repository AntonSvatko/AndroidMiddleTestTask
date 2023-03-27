package com.youarelaunched.challenge

import com.youarelaunched.challenge.repositores.VendorsFakeRepository
import com.youarelaunched.challenge.repositories.com.youarelaunched.challenge.repositores.VendorsFakeRepositoryError
import com.youarelaunched.challenge.rule.MainDispatcherRule
import com.youarelaunched.challenge.ui.screen.view.VendorsVM
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class VendorTest {
    private lateinit var viewModel: VendorsVM

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testVendorListLoadingSuccess() = runTest {
        viewModel = VendorsVM(VendorsFakeRepository())
        viewModel.getVendors()

        Assert.assertFalse(viewModel.uiState.first().vendors.isNullOrEmpty())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testVendorListLoadingError() = runTest {
        viewModel = VendorsVM(VendorsFakeRepositoryError())
        viewModel.getVendors()

        Assert.assertNull(viewModel.uiState.first().vendors)
    }
}