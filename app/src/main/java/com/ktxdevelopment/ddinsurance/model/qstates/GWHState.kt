package com.ktxdevelopment.ddinsurance.model.qstates

import com.ktxdevelopment.ddinsurance.survey.questions.Gender

data class GWHState(
    var gender: Gender = Gender.Male,
    var weight: Int = 60,
    var height: Int = 170
)