package com.ktxdevelopment.ddinsurance.model.qstates

import com.ktxdevelopment.ddinsurance.survey.questions.Answer

data class SmokeDrinkState(
    var smoke: Answer = Answer.No,
    var drink: Answer = Answer.No
)