package com.ktxdevelopment.ddinsurance.survey.questions


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.model.qstates.AllergyActivityState
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper
import com.ktxdevelopment.ddinsurance.survey.components.SliderQuestion

@Composable
fun AllergyActivityQuestion(
    onAllergyStateChange: (AllergyActivityState) -> Unit,
    modifier: Modifier
) {
    val state: AllergyActivityState by remember { mutableStateOf(
        AllergyActivityState(5F, Answer.No, null)
    ) }

    Column(modifier = modifier) {

        SliderQuestion(
            titleResourceId = R.string.your_activity,
            value = state.activity,
            onValueChange = { state.activity = it; onAllergyStateChange(state.copy(activity = it)) },
            startTextResource = R.string._0,
            neutralTextResource = R.string._5,
            endTextResource = R.string._10,
            modifier = Modifier.padding(bottom = 32.dp),)

        AllergyQuestion(
            state,
            onAnswerChanged = {
                state.allergyAnswer = it
                onAllergyStateChange(state.copy(allergyAnswer = it))
            },
            onAllergyTextChanged = {
                state.allergies = it
                onAllergyStateChange(state.copy(allergies = it))
            }
        )
    }
}



@Composable
fun AllergyQuestion(
    state: AllergyActivityState,
    onAnswerChanged: (Answer) -> Unit,
    onAllergyTextChanged: (String) -> Unit
) {
    var answer: Answer by remember { mutableStateOf(Answer.No) }

    Column {

        QuestionWrapper(titleResourceId = R.string.do_you_have_allergy) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center
            ) {

                AnswerOption(
                    answer = Answer.No,
                    selectedAnswer = state.allergyAnswer
                ) {
                    onAnswerChanged(it)
                    answer = it
                }

                AnswerOption(
                    answer = Answer.Yes,
                    selectedAnswer = answer
                ) {
                    onAnswerChanged(it)
                    answer = it
                }
            }
        }
        if (answer == Answer.Yes) AllergyAddView { onAllergyTextChanged(it) }
    }
}

@Composable
fun AllergyAddView(onAllergyChanged: (String) -> Unit) {

    var allergy by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {

        Text(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 8.dp) ,text = "Please mention:", style = MaterialTheme.typography.bodyMedium)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = allergy,
            onValueChange = { allergy = it ; onAllergyChanged(allergy) },
            label = { Text("Enter your allergies") },
            visualTransformation = VisualTransformation.None, // Show all entered digits
            singleLine = true
        )
    }
}