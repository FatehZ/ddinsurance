package com.ktxdevelopment.ddinsurance.survey

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ktxdevelopment.ddinsurance.model.qstates.AllergyActivityState
import com.ktxdevelopment.ddinsurance.model.qstates.DiseaseState
import com.ktxdevelopment.ddinsurance.model.qstates.GWHState
import com.ktxdevelopment.ddinsurance.model.qstates.SmokeDrinkState
import com.ktxdevelopment.ddinsurance.model.SurveyQuestion
import com.ktxdevelopment.ddinsurance.model.SurveyScreenData
import com.ktxdevelopment.ddinsurance.survey.questions.Answer
import com.ktxdevelopment.ddinsurance.survey.questions.Gender

const val simpleDateFormatPattern = "EEE, MMM d, yyyy"

class SurveyViewModel : ViewModel() {


    private val questionOrder: List<SurveyQuestion> = listOf(
        SurveyQuestion.BIRTH_DATE,
        SurveyQuestion.GWH,
        SurveyQuestion.SMK_DRK,
        SurveyQuestion.DISEASE,
        SurveyQuestion.ALLERGY_ACTV,
    )
    private var questionIndex = 0

    private val _birthDateResponse = mutableStateOf<Long?>(null)
    val birthDateResponse: Long? get() = _birthDateResponse.value

    private val _gwhResponse = mutableStateOf<GWHState?>(GWHState())
    val gwhResponse: GWHState? get() = _gwhResponse.value

    private val _allergyActivityResponse = mutableStateOf<AllergyActivityState?>(
        AllergyActivityState()
    )
    val allergyResponse: AllergyActivityState? get() = _allergyActivityResponse.value

    private val _smokeDrinkResponse = mutableStateOf<SmokeDrinkState?>(SmokeDrinkState())
    val smokeDrinkResponse: SmokeDrinkState? get() = _smokeDrinkResponse.value

    private val _diseaseResponse = mutableStateOf<DiseaseState?>(DiseaseState())
    val diseaseResponse: DiseaseState? get() = _diseaseResponse.value


    private val _surveyScreenData = mutableStateOf(createSurveyScreenData())
    val surveyScreenData: SurveyScreenData
        get() = _surveyScreenData.value

    private val _isNextEnabled = mutableStateOf(false)
    val isNextEnabled: Boolean get() = _isNextEnabled.value


    fun onBirthdateResponse(timestamp: Long) {
        _birthDateResponse.value = timestamp
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onGenderChange(gender: Gender) {
        _gwhResponse.value = _gwhResponse.value?.copy(gender = gender)
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onWeightChange(weight: Int) {
        _gwhResponse.value = _gwhResponse.value?.copy(weight = weight)
        _isNextEnabled.value = getIsNextEnabled()
    }


    fun onHeightChange(height: Int) {
        _gwhResponse.value = _gwhResponse.value?.copy(height = height)
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onSmokeChanged(answer: Answer) {
        _smokeDrinkResponse.value = smokeDrinkResponse?.copy(smoke = answer)
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onDrinkChanged(answer: Answer) {
        _smokeDrinkResponse.value = smokeDrinkResponse?.copy(drink = answer)
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onDiseaseStateChange(diseaseState: DiseaseState) {
        _diseaseResponse.value = diseaseState
        _isNextEnabled.value = getIsNextEnabled()
    }

    fun onAllergyStateChange(allergyActivityState: AllergyActivityState) {
        _allergyActivityResponse.value = allergyActivityState
        _isNextEnabled.value = getIsNextEnabled()
    }

    private fun getIsNextEnabled(): Boolean {
        return when (questionOrder[questionIndex]) {
            SurveyQuestion.BIRTH_DATE -> _birthDateResponse.value != null
            SurveyQuestion.GWH -> _gwhResponse.value != null
            SurveyQuestion.ALLERGY_ACTV -> _allergyActivityResponse.value != null
            SurveyQuestion.SMK_DRK -> _smokeDrinkResponse.value != null
            SurveyQuestion.DISEASE -> _diseaseResponse.value != null
        }
    }

    //
    //
    //


    private fun createSurveyScreenData(): SurveyScreenData = SurveyScreenData(
        questionIndex = questionIndex,
        questionCount = questionOrder.size,
        shouldShowPreviousButton = questionIndex > 0,
        shouldShowDoneButton = questionIndex == questionOrder.size - 1,
        surveyQuestion = questionOrder[questionIndex],
    )


    fun onBackPressed(): Boolean {
        if (questionIndex == 0) {
            return false
        }
        changeQuestion(questionIndex - 1)
        return true
    }


    fun onPreviousPressed() {
        if (questionIndex == 0) {
            throw IllegalStateException("onPreviousPressed when on question 0")
        }
        changeQuestion(questionIndex - 1)
    }


    fun onNextPressed() {
        changeQuestion(questionIndex + 1)
    }

    private fun changeQuestion(newQuestionIndex: Int) {
        questionIndex = newQuestionIndex
        _isNextEnabled.value = getIsNextEnabled()
        _surveyScreenData.value = createSurveyScreenData()
    }

    fun onDonePressed(onSurveyComplete: () -> Unit) {
        onSurveyComplete()
    }


    @Suppress("UNCHECKED_CAST")
    class SurveyViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SurveyViewModel::class.java)) {
                return SurveyViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}