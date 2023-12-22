package com.ktxdevelopment.ddinsurance.login

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ktxdevelopment.ddinsurance.login.vm.WelcomeViewModel
import com.ktxdevelopment.ddinsurance.login.vm.WelcomeViewModelFactory

@Composable
fun WelcomeRoute(onSignInAsGuest: () -> Unit, ) {
    val welcomeViewModel: WelcomeViewModel = viewModel(factory = WelcomeViewModelFactory())

    WelcomeScreen(
        onSignInSignUp = { email ->
            welcomeViewModel.signInAsGuest(onSignInAsGuest)
        },
        onSignInAsGuest = {
            welcomeViewModel.signInAsGuest(onSignInAsGuest)
        },
    )
}
