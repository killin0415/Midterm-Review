package com.example.f74124765_5504_1.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class HnWData(
    var name: String = "0",
    var num: Int = 0
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HnW(title: String, data: HnWData, kc: SoftwareKeyboardController?) {
    Row {
        Text(
            title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
        OutlinedTextField(
            data.name,
            onValueChange = { data.name = it },
            maxLines = 1,
            modifier = Modifier.padding(5.dp).width(100.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    data.num = data.name.toInt()
                    data.name = data.num.toString()
                    kc?.hide()
                }
            )
        )
    }
}