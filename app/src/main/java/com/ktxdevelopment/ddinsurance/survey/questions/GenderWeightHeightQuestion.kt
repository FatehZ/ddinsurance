@file:OptIn(ExperimentalMaterial3Api::class)

package com.ktxdevelopment.ddinsurance.survey.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper


@Composable
fun GenderWeightHeightQuestion(modifier: Modifier = Modifier, onGenderChange: (Gender) -> Unit, onWeightChange: (Int) -> Unit, onHeightChange: (Int) -> Unit){
    Column(modifier = modifier) {

        GenderQuestion(onGenderChange)

        Spacer(modifier = Modifier.height(16.dp))

        WeightQuestion(onWeightChange)

        Spacer(modifier = Modifier.height(16.dp))

        HeightQuestion(onHeightChange)

    }
}

@Composable
fun GenderQuestion(onGenderChange: (Gender) -> Unit) {
    var selectedGender: Gender by remember { mutableStateOf(Gender.Male) }

    QuestionWrapper(titleResourceId = R.string.what_is_gender) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            GenderOption(
                gender = Gender.Male,
                selectedGender = selectedGender,
                onGenderSelected = {
                    selectedGender = it
                    onGenderChange(it)
                }
            )

            GenderOption(
                gender = Gender.Female,
                selectedGender = selectedGender,
                onGenderSelected = {
                    selectedGender = it
                    onGenderChange(it)
                }
            )
        }
    }
}

@Composable
fun GenderOption(gender: Gender, selectedGender: Gender, onGenderSelected: (Gender) -> Unit) {
    val backgroundColor = if (gender == selectedGender) { MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) } else { Color.Transparent }

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .clickable { onGenderSelected(gender) }
            .padding(vertical = 14.dp, horizontal = 24.dp)
    ) {
        Row {
            Icon(
                imageVector = when (gender) {
                    Gender.Female -> Icons.Default.Female
                    Gender.Male -> Icons.Default.Male
                },
                contentDescription = null,
                tint = if (gender == selectedGender) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onBackground
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(style = MaterialTheme.typography.bodyLarge, text = gender.name)
        }
    }
}

enum class Gender {
    Male, Female
}

@Composable
fun WeightQuestion(onWeightChange: (Int) -> Unit) {
    var weight by remember { mutableStateOf("60") }

    QuestionWrapper(titleResourceId = R.string.enter_weight) {
        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = weight,
            onValueChange = {
                if (it.length <= 3) {
                    weight = it
                    if (it != "") {
                        onWeightChange(it.toInt())
                    }
                }
                            },
            label = { Text("Enter weight") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            visualTransformation = VisualTransformation.None, // Show all entered digits
            singleLine = true)
        }
    }
}

@Composable
fun HeightQuestion(onHeightChange: (Int) -> Unit) {
    var height by remember { mutableStateOf("170") }

    QuestionWrapper(titleResourceId = R.string.enter_height) {

        Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {

            OutlinedTextField(
                value = height,
                onValueChange = {
                    if (it.length <= 3) {
                        height = it
                        if (it != "") {
                            onHeightChange(height.toInt())
                        }
                    }
                                },
                label = { Text("Enter your height") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = VisualTransformation.None, // Show all entered digits
                singleLine = true,
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}
