package com.ktxdevelopment.ddinsurance.model.qstates

import com.ktxdevelopment.ddinsurance.survey.questions.Answer

data class DiseaseState(
    var present: Answer = Answer.No,
    var diseases: MutableSet<String>? = null
)