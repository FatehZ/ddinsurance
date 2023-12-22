package com.ktxdevelopment.ddinsurance.survey.questions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.survey.components.DateQuestion

@Composable
fun BirthDateQuestion(dateInMillis: Long?, onClick: () -> Unit, modifier: Modifier = Modifier, ) {
    DateQuestion(
        titleResourceId = R.string.date_specify,
        directionsResourceId = R.string.select_date,
        dateInMillis = dateInMillis,
        onClick = onClick,
        modifier = modifier,
    )
}