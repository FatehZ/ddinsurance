package com.ktxdevelopment.ddinsurance.model.qstates

import com.ktxdevelopment.ddinsurance.survey.questions.Answer

data class AllergyActivityState(
    var activity: Float = 5F,
    var allergyAnswer: Answer = Answer.No,
    var allergies: String? = null
)