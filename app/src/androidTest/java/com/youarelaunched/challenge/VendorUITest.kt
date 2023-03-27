package com.youarelaunched.challenge

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.youarelaunched.challenge.data.repository.model.Vendor
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.screen.state.VendorsScreenUiState
import com.youarelaunched.challenge.ui.screen.view.VendorsScreen
import com.youarelaunched.challenge.ui.theme.VendorAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VendorUITest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun testContentVisible() {
        val uiState = VendorsScreenUiState(
            listOf(
                Vendor(
                    0,
                    "testCompanyName",
                    "https://cdn-staging.chatsumer.app/eyJidWNrZXQiOiJjaGF0c3VtZXItZ2VuZXJhbC1zdGFnaW5nLXN0b3JhZ2UiLCJrZXkiOiIxMy84ZjMzZTgyNy0yNzIxLTQ3ZjctYjViNS0zM2Q5Y2E2MTM1OGQuanBlZyJ9",
                    "areaTest",
                    true,
                    null,
                    null
                )
            )
        )
        setUpComposerRole(uiState)

        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.tag_vendor_item))
            .assertIsDisplayed()
    }

    @Test
    fun testNoResult() {
        val uiState = VendorsScreenUiState(listOf())
        setUpComposerRole(uiState)

        val noResultTitle = composeRule.activity.getString(R.string.no_result_title)
        val noResultDescription =
            composeRule.activity.getString(R.string.no_result_description)

        composeRule.onNodeWithText(noResultTitle).assertIsDisplayed()
        composeRule.onNodeWithText(noResultDescription).assertIsDisplayed()
    }

    private fun setUpComposerRole(uiState: VendorsScreenUiState) {
        composeRule.setContent {
            VendorAppTheme {
                VendorsScreen(
                    uiState = uiState,
                    onSearchClick = {},
                    onSearchQuery = {}
                )
            }
        }
    }
}