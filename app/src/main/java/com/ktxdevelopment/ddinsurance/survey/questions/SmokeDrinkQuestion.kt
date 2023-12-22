package com.ktxdevelopment.ddinsurance.survey.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper

@Composable
fun SmokeDrinkQuestion(
    onSmokeSateChange: (Answer) -> Unit,
    onDrinkStateChange: (Answer) -> Unit,
    modifier: Modifier
) {
    var smokeAnswer by remember { mutableStateOf(Answer.No) }
    var drinkAnswer by remember { mutableStateOf(Answer.No) }

    Column(modifier = modifier) {
        QuestionWrapper(titleResourceId = R.string.do_you_smoke) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center) {

                AnswerOption(
                    answer = Answer.No,
                    selectedAnswer = smokeAnswer
                ) {
                    if (it != smokeAnswer) {
                        onSmokeSateChange(it)
                        smokeAnswer = it
                    }
                }

                AnswerOption(
                    answer = Answer.Yes,
                    selectedAnswer = smokeAnswer
                ) {
                    if (it != smokeAnswer) {
                        onSmokeSateChange(it)
                        smokeAnswer = it
                    }
                }
            }
        }

        Spacer(modifier = Modifier.size(32.dp))

        QuestionWrapper(titleResourceId = R.string.do_you_drink) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp), horizontalArrangement = Arrangement.Center
            ) {

                AnswerOption(
                    answer = Answer.No,
                    selectedAnswer = drinkAnswer
                ) {
                    if (it != drinkAnswer) {
                        onDrinkStateChange(it)
                        drinkAnswer = it
                    }
                }

                AnswerOption(
                    answer = Answer.Yes,
                    selectedAnswer = drinkAnswer
                ) {
                    if (it != drinkAnswer) {
                        onDrinkStateChange(it)
                        drinkAnswer = it
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerOption(answer: Answer, selectedAnswer: Answer, onAnswerSelected: (Answer) -> Unit) {
    val backgroundColor = if (answer == selectedAnswer) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .clickable { onAnswerSelected(answer) }
            .padding(vertical = 14.dp, horizontal = 24.dp)
    ) {
        Row {
            Icon(
                imageVector = when (answer) {
                    Answer.No -> Icons.Default.Cancel
                    Answer.Yes -> Icons.Default.CheckCircleOutline
                },
                contentDescription = null,
                tint = if (answer == selectedAnswer) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onBackground
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(style = MaterialTheme.typography.bodyLarge, text = answer.name)
        }
    }
}


enum class Answer(val textResId: Int) {
    Yes(R.string.answer_yes),
    No(R.string.answer_no);
}