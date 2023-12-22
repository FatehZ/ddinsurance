package com.ktxdevelopment.ddinsurance.survey

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.compose.BackHandler
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.ktxdevelopment.ddinsurance.model.SurveyQuestion
import com.ktxdevelopment.ddinsurance.survey.questions.AllergyActivityQuestion
import com.ktxdevelopment.ddinsurance.survey.questions.BirthDateQuestion
import com.ktxdevelopment.ddinsurance.survey.questions.DiseaseQuestion
import com.ktxdevelopment.ddinsurance.survey.questions.GenderWeightHeightQuestion
import com.ktxdevelopment.ddinsurance.survey.questions.SmokeDrinkQuestion

private const val CONTENT_ANIMATION_DURATION = 300


@Composable
fun SurveyRoute(
    onSurveyComplete: () -> Unit, onNavUp: () -> Unit,
) {
    val viewModel: SurveyViewModel = viewModel(factory = SurveyViewModel.SurveyViewModelFactory())
    val surveyScreenData = viewModel.surveyScreenData
    BackHandler { if (!viewModel.onBackPressed()) { onNavUp() } }


    SurveyQuestionsScreen(
        surveyScreenData = surveyScreenData,
        isNextEnabled = viewModel.isNextEnabled,
        onClosePressed = { onNavUp() },
        onPreviousPressed = { viewModel.onPreviousPressed() },
        onNextPressed = { viewModel.onNextPressed() },
        onDonePressed = { viewModel.onDonePressed(onSurveyComplete) }
    ) { paddingValues ->

        val modifier = Modifier.padding(paddingValues)

        AnimatedContent(
            targetState = surveyScreenData, transitionSpec = {
                val animationSpec: TweenSpec<IntOffset> = tween(CONTENT_ANIMATION_DURATION)
                val direction = getTransitionDirection(initialIndex = initialState.questionIndex, targetIndex = targetState.questionIndex,)
                slideIntoContainer(towards = direction, animationSpec = animationSpec,) togetherWith slideOutOfContainer(towards = direction, animationSpec = animationSpec) }, label = "surveyScreenDataAnimation"
        )
        { targetState ->

            when (targetState.surveyQuestion) {
                SurveyQuestion.BIRTH_DATE -> {
                    val supportFragmentManager = LocalContext.current.findActivity().supportFragmentManager

                    BirthDateQuestion(
                        dateInMillis = viewModel.birthDateResponse,
                        onClick = { showBirthDatePicker(
                            date = viewModel.birthDateResponse,
                            supportFragmentManager = supportFragmentManager,
                            onDateSelected = viewModel::onBirthdateResponse)
                        }, modifier = modifier,
                    )
                }

                SurveyQuestion.GWH -> GenderWeightHeightQuestion(
                    modifier = modifier,
                    onGenderChange = viewModel::onGenderChange,
                    onWeightChange = viewModel::onWeightChange,
                    onHeightChange = viewModel::onHeightChange
                )

                SurveyQuestion.SMK_DRK -> SmokeDrinkQuestion(
                    modifier = modifier,
                    onSmokeSateChange = viewModel::onSmokeChanged,
                    onDrinkStateChange = viewModel::onDrinkChanged
                )

                SurveyQuestion.DISEASE -> DiseaseQuestion(
                    modifier = modifier,
                    diseaseState = viewModel.diseaseResponse!!,
                    onDiseaseStateChange = viewModel::onDiseaseStateChange
                )

                SurveyQuestion.ALLERGY_ACTV -> AllergyActivityQuestion(
                    modifier = modifier,
                    onAllergyStateChange = viewModel::onAllergyStateChange
                )
            }
        }
    }
}





private fun getTransitionDirection(initialIndex: Int, targetIndex: Int): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) { AnimatedContentTransitionScope.SlideDirection.Left
    } else { AnimatedContentTransitionScope.SlideDirection.Right }
}


private fun showBirthDatePicker(date: Long?, supportFragmentManager: FragmentManager, onDateSelected: (date: Long) -> Unit) {
    val picker = MaterialDatePicker.Builder.datePicker().setSelection(date).build()
    picker.show(supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener { picker.selection?.let { onDateSelected(it) } }
}

private tailrec fun Context.findActivity(): AppCompatActivity =
    when (this) {
        is AppCompatActivity -> this
        is ContextWrapper -> this.baseContext.findActivity()
        else -> throw IllegalArgumentException("Could not find activity!")
    }
