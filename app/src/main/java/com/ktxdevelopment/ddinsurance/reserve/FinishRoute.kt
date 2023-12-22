package com.ktxdevelopment.ddinsurance.reserve

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper


@Composable
fun FinishRoute() {

    QuestionWrapper(titleResourceId = R.string.meeting_setup) {
        Column(modifier = Modifier.padding(42.dp)) {
            Image(painter = painterResource(id = R.drawable.check_box_img), contentDescription = "checkbox")
        }
    }
}