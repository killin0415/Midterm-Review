package com.example.f74124765_5504_1.views


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                text = { Text(it, modifier = Modifier.wrapContentWidth().align(Alignment.Start)) },
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            )
        }
    }
}

@Composable
fun ProjectDropDown(dropDownList: List<String>, text: String, onChange: (String) -> Unit, modifier: Modifier = Modifier) {
    var isOpen by remember { mutableStateOf(false) }
    Box {
        Column(modifier = modifier) {
            OutlinedTextField(
                value = text,
                onValueChange = onChange,
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
            DropDownList(
                requestToOpen = isOpen,
                list = dropDownList,
                request = { isOpen = it },
                selectedString = onChange
            )
        }
        Spacer(
            modifier = Modifier.matchParentSize().background(Color.Transparent).padding(10.dp)
                .clickable(onClick = { isOpen = true })
        )
    }
}