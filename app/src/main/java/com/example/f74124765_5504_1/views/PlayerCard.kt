package com.example.f74124765_5504_1.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.f74124765_5504_1.R

@Composable
fun PlayerCard(name: String, sports: String, nation: String, age: Int, BMI: Double, food: List<String>, modifier: Modifier = Modifier) {

    val fontSize = 20.sp
    val imagePath = when (sports) {
        "baseball" -> R.drawable.baseball
        "basketball" -> R.drawable.basketball
        else -> R.drawable.hit
    }

    val nationPath = when (nation) {
        "japan" -> R.drawable.japan
        "tw" -> R.drawable.tw
        else -> R.drawable.us
    }

    Card (modifier = modifier) {
        Column(modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
        ) {
            Text(
                text = name,
                fontSize = fontSize,
                modifier = Modifier.padding(5.dp).fillMaxWidth().height(25.dp),
                textAlign = TextAlign.End
            )
            Row(modifier = Modifier.fillMaxWidth().height(150.dp), horizontalArrangement = Arrangement.Center) {
                Image(painter = painterResource(imagePath),
                    contentDescription = null,
                    modifier = Modifier.width(150.dp).height(100.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.width(70.dp))
                Column(modifier = Modifier.width(150.dp).height(150.dp), verticalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(nationPath),
                        modifier = Modifier.fillMaxWidth().height(60.dp),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        "${age}歲",
                        fontSize = fontSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().height(20.dp)
                    )
                    Text(
                        "BMI:${String.format("%.2f", BMI)}",
                        fontSize = fontSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().height(20.dp)
                    )
                }
                Row {
                    food.forEach {
                        val path = when(it) {
                            "sushi" -> R.drawable.sushi
                            "ham" -> R.drawable.ham
                            else -> R.drawable.bubble
                        }
                        println(path)
                        Image(
                            painter = painterResource(path),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}