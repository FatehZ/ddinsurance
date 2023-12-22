package com.ktxdevelopment.ddinsurance.result

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapperScrollSafe
import com.ktxdevelopment.ddinsurance.theme.AutoComplete


@Composable
fun ResultRoute(onHospitalSelected: (hospital: String) -> Unit) {
    val resultViewModel: ResultViewModel = viewModel(factory = ResultViewModel.ResultViewModelFactory())

    QuestionWrapperScrollSafe(titleResourceId = R.string.where_do_you_live) {
        ResultScreen(resultViewModel.residenceSites, resultViewModel.shownHospitals, resultViewModel::onDistrictChanged, onHospitalSelected = onHospitalSelected)
    }
}


@Composable
fun ResultScreen(residenceSites: List<String>, shownHospitals: Array<String> = arrayOf(), onHospitalDistrictChanged: (district: String) -> Unit, onHospitalSelected: (hospital: String) -> Unit) {

    val TAG = "TAG_LTS"

    Column(modifier = Modifier.heightIn(1000.dp))
    {
        AutoComplete(residenceSites) {
            Log.i(TAG, "ResultScreen: 3")
            onHospitalDistrictChanged(it)
        }


        if (shownHospitals.isNotEmpty()) {
            shownHospitals.forEach { item ->
                Row(modifier = Modifier.clickable(onClick = { onHospitalSelected(item) })) {
                    HospitalCard(text = item)
                }
            }
        }
    }
}


@Composable
fun HospitalCard(text: String) {
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(4.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xff333333),
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xff00ffff),
                    shape = MaterialTheme.shapes.small
                )
                .padding(vertical = 16.dp, horizontal = 30.dp)
        )
        Spacer(Modifier.height(12.dp))
    }
}
