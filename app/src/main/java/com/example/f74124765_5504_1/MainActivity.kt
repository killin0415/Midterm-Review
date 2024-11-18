package com.example.f74124765_5504_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.f74124765_5504_1.ui.theme.F74124765_5504_1Theme
import com.example.f74124765_5504_1.views.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F74124765_5504_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val innerPadding = 5.dp
                        val kc = LocalSoftwareKeyboardController.current
                        Text(
                            text = "球員卡產生器",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(10.dp),
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Column(modifier = Modifier.padding(5.dp)) {

                            var nameInp by remember { mutableStateOf("") }
                            var name by remember { mutableStateOf("") }

                            val radioButtons = remember {
                                mutableStateListOf(
                                    ToggleableInfo(
                                        isChecked = true,
                                        text = "日本"
                                    ),
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = "美國"
                                    ),
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = "台灣"
                                    ),
                                )
                            }

                            val checkboxes = remember {
                                mutableStateListOf(
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = "壽司"
                                    ),
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = "漢堡"
                                    ),
                                    ToggleableInfo(
                                        isChecked = false,
                                        text = "珍奶"
                                    ),
                                )
                            }

                            val nation by remember {
                                derivedStateOf { radioButtons.find { it.isChecked } }
                            }

                            var sport by remember { mutableStateOf("") }

                            var testB by remember { mutableStateOf(false) }
                            var selectedDate by remember { mutableStateOf<Long?>(null) }


                            val hData by remember { mutableStateOf(HnWData()) }
                            val wData by remember { mutableStateOf(HnWData()) }
                            Row(modifier = Modifier.padding(innerPadding)) {
                                Text(
                                    "姓名：",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                                OutlinedTextField(
                                    nameInp,
                                    onValueChange = { nameInp = it },
                                    maxLines = 1,
                                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                                    keyboardOptions = KeyboardOptions(
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = {
                                            name = nameInp
                                            kc?.hide()
                                        }
                                    )
                                )
                            }
                            Row(modifier = Modifier.padding(innerPadding)) {
                                Text(
                                    "項目：",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                                ProjectDropDown(
                                    listOf(
                                        "棒球",
                                        "籃球",
                                        "羽球"
                                    ),
                                    sport,
                                    onChange = { sport = it }
                                )
                            }

                            Row(modifier = Modifier.padding(innerPadding)) {

                                Text(
                                    "國籍：",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                                Nations(radioButtons)
                            }

                            Row(
                                modifier = Modifier.padding(innerPadding).fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {


                                Text(
                                    "生日：${selectedDate?.let { convertMillisToDate(it) } ?: "2024/11/11"}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Button(
                                    content = { Text("日期") },
                                    onClick = {
                                        testB = true
                                    },
                                )

                                if (testB) {
                                    DatePickerModal(
                                        onDateSelected = { selectedDate = it },
                                        onDismiss = { testB = false }
                                    )
                                }
                            }

                            Row(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {

                                HnW("身高：", hData, kc)
                                HnW("體重：", wData, kc)


                            }

                            Row(modifier = Modifier.padding(innerPadding)) {

                                Text(
                                    "食物：",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                                Checkboxes(checkboxes)
                            }
                            Button(
                                content = { Text("產生") },
                                onClick = { },
                                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                            )
                            val sports = when (sport) {
                                "棒球" -> "baseball"
                                "籃球" -> "basketball"
                                else -> "hit"
                            }
                            val nations = when (nation!!.text) {
                                "日本" -> "japan"
                                "台灣" -> "tw"
                                else -> "us"
                            }
                            val age =
                                2024 - (selectedDate?.let { convertMillisToDate(it) } ?: "2024/11/11").substring(0, 4)
                                    .toInt()
                            val BMI = wData.num.toDouble() / (hData.num.toDouble() * hData.num.toDouble())


                            PlayerCard(name, sports, nations, age, BMI, checkboxes.filter { it.isChecked }.map {
                                when (it.text) {
                                    "壽司" -> "sushi"
                                    "漢堡" -> "ham"
                                    else -> "bubble"
                                }
                            }, modifier = Modifier.fillMaxSize().padding(innerPadding))
                        }


                    }
                }
            }
        }
    }
}



