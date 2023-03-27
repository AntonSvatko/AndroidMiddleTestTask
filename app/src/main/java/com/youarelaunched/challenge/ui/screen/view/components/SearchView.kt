package com.youarelaunched.challenge.ui.screen.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.youarelaunched.challenge.middle.R
import com.youarelaunched.challenge.ui.theme.VendorAppTheme


@Composable
fun SearchView(onSearchQuery: (String) -> Unit, onSearchClick: (String) -> Unit) {
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 2.dp),
        backgroundColor = White,
        elevation = 10.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        TextField(
            value = searchText,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClick(searchText.text)
                }
            ),
            placeholder = {
                Text(
                    text = "Search...",
                    style = VendorAppTheme.typography.subtitle2.copy(color = VendorAppTheme.colors.text),
                )
            },
            onValueChange = { value ->
                onSearchQuery(value.text)
                searchText = value
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = VendorAppTheme.typography.subtitle1.copy(color = VendorAppTheme.colors.textDark),
            trailingIcon = {
                if (searchText != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            searchText =
                                TextFieldValue("")
                            onSearchClick(searchText.text)
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            onSearchClick(searchText.text)
                        },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape,
            colors = TextFieldDefaults.textFieldColors(
                textColor = Gray,
                cursorColor = Gray,
                trailingIconColor = Gray,
                backgroundColor = White,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
                disabledIndicatorColor = Transparent
            )
        )
    }
}