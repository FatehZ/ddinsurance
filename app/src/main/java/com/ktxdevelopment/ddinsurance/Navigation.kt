package com.ktxdevelopment.ddinsurance

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ktxdevelopment.ddinsurance.Destinations.FINISH_ROUTE
import com.ktxdevelopment.ddinsurance.Destinations.RESERVE_ROUTE
import com.ktxdevelopment.ddinsurance.Destinations.RESULT_ROUTE
import com.ktxdevelopment.ddinsurance.Destinations.SURVEY_ROUTE
import com.ktxdevelopment.ddinsurance.Destinations.WELCOME_ROUTE
import com.ktxdevelopment.ddinsurance.login.WelcomeRoute
import com.ktxdevelopment.ddinsurance.reserve.FinishRoute
import com.ktxdevelopment.ddinsurance.reserve.ReserveRoute
import com.ktxdevelopment.ddinsurance.result.ResultRoute
import com.ktxdevelopment.ddinsurance.survey.SurveyRoute


object Destinations {
    const val WELCOME_ROUTE = "welcome"
    const val RESULT_ROUTE = "result"
    const val SURVEY_ROUTE = "survey"
    const val RESERVE_ROUTE = "reserve"
    const val FINISH_ROUTE = "finish"
}


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = WELCOME_ROUTE) {
        composable(WELCOME_ROUTE) {
            WelcomeRoute { navController.navigate(SURVEY_ROUTE) }
        }

        composable(SURVEY_ROUTE) {
            SurveyRoute(onSurveyComplete = { navController.navigate(RESULT_ROUTE) }, onNavUp = navController::navigateUp)
        }

        composable(RESULT_ROUTE) {
            ResultRoute(onHospitalSelected = { navController.navigate(RESERVE_ROUTE) })
        }

        composable(RESERVE_ROUTE) {
            ReserveRoute(onMeetingReserved = { navController.navigate(FINISH_ROUTE) })
        }

        composable(FINISH_ROUTE) {
            FinishRoute()
        }
    }
}
