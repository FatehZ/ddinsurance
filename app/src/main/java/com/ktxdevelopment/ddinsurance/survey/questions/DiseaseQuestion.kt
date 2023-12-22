package com.ktxdevelopment.ddinsurance.survey.questions


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.model.qstates.DiseaseState
import com.ktxdevelopment.ddinsurance.survey.components.MultipleChoiceQuestion
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper


@Composable
fun DiseaseQuestion(
    onDiseaseStateChange: (DiseaseState) -> Unit,
    modifier: Modifier,
    diseaseState: DiseaseState
) {

    Column(modifier = modifier) {

        QuestionWrapper(titleResourceId = R.string.do_you_have_genetic) {
            Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center) {

                AnswerOption(answer = Answer.No, selectedAnswer = diseaseState.present) {
                    onDiseaseStateChange(diseaseState.copy(present = Answer.No, diseases = null)) }

                AnswerOption(answer = Answer.Yes, selectedAnswer = diseaseState.present) {
                    onDiseaseStateChange(diseaseState.copy(present = Answer.Yes, diseases = mutableSetOf())) }
            }
        }

        if (diseaseState.present == Answer.Yes) {
            DiseasesView(diseaseState, onDiseaseStateChange)
        }
    }
}


@SuppressLint("MutableCollectionMutableState")
@Composable
fun DiseasesView(diseaseState: DiseaseState, onDiseaseStateChange: (DiseaseState) -> Unit) {

    val otherEnabled = false

    val diseases: ArrayList<String> = arrayListOf(
        "Down Syndrome", "Cystic Fibrosis", "Sickle Cell Anemia", "Hemophilia",
        "Muscular Dystrophy", "Huntington's Disease", "Fragile X Syndrome",
        "Neurofibromatosis", "Thalassemia", "Albinism", "Tay-Sachs Disease", "Phenylketonuria (PKU)",
        "Celiac Disease", "Marfan Syndrome", "Polycystic Kidney Disease (PKD)"
    )

    var customDisease by remember { mutableStateOf("") }


    Column {

        MultipleChoiceQuestion(
            possibleAnswers = diseases,
            selectedAnswers = diseaseState.diseases,
            onOptionSelected = { selected, answer ->
                if (selected) {
                    onDiseaseStateChange(diseaseState.copy(diseases = diseaseState.diseases!!.toMutableSet().apply { add(answer) }))
                } else {
                    onDiseaseStateChange(diseaseState.copy(diseases = diseaseState.diseases!!.toMutableSet().apply { remove(answer) }))
                }
            },
            modifier = Modifier.padding(16.dp)
        )
        if (otherEnabled) {

            Text(text = "Other:")

            Row {
                OutlinedTextField(
                    value = customDisease,
                    onValueChange = {
                        if (it.length < 40) {
                            customDisease = it
                        }
                    },
                    label = { Text("Enter disease name") },
                    visualTransformation = VisualTransformation.None,
                    singleLine = true
                )

                Button(modifier = Modifier.padding(start = 8.dp),
                    onClick = {
                        if (customDisease.length >= 3) {
                            diseases.add(customDisease)
                            onDiseaseStateChange(
                                diseaseState.copy(
                                    diseases = diseaseState.diseases!!.toMutableSet()
                                        .apply { add(customDisease) })
                            )
                            customDisease = ""
                        }
                    }) { Text(text = "Add") }
            }
        }
    }
}


@Composable
fun OptionCard(option: String, isSelected: Boolean, onOptionSelected: (String) -> Unit) {
    Card(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.White)
            .clickable { onOptionSelected(option) },
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalArrangement = Arrangement.Center) {
            Text(text = option, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary)
        }
    }
}