package com.ktxdevelopment.ddinsurance.reserve

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ktxdevelopment.ddinsurance.R
import com.ktxdevelopment.ddinsurance.model.Doctor
import com.ktxdevelopment.ddinsurance.model.ReserveSelection
import com.ktxdevelopment.ddinsurance.survey.components.QuestionWrapper

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ReserveRoute(onMeetingReserved: (ReserveSelection) -> Unit) {
    val doctors = arrayOf(
        Doctor("J. Arnold", arrayOf("13:00 - 14:00")),
        Doctor("H. Ronald", arrayOf("11:00 - 12:00", "14:00 - 15:00", "16:00 - 17:00")),
        Doctor("T. Albert", arrayOf("13:00 - 14:00", "14:00 - 15:00"))
    )

    QuestionWrapper(titleResourceId = R.string.select_reserve_date) {

        var selectedOption: ReserveSelection? by remember { mutableStateOf(null) }

        Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            doctors.forEach { doctor ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = doctor.name, style = MaterialTheme.typography.bodyLarge)

                        Spacer(modifier = Modifier.height(12.dp))
                        Divider(thickness = 2.dp)
                        Spacer(modifier = Modifier.height(4.dp))

                        doctor.options.forEach { option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = (if (selectedOption == null) false else {
                                        doctor.name == selectedOption!!.doctorName && option == selectedOption!!.time
                                    }),
                                    onClick = { selectedOption = ReserveSelection(doctor.name, option) },
                                    modifier = Modifier
                                        .padding(4.dp)
                                )
                                Text(text = option, modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            FilledIconButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onMeetingReserved(selectedOption!!) },
                enabled = selectedOption != null,
            ) {
                Text(text = stringResource(id = R.string.done), color = if (selectedOption == null) Color.Gray else Color.White)
            }
        }
    }
}