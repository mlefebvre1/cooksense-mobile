package org.chef.cooksense

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.chef.cooksense.auth.AuthViewModel
import org.chef.cooksense.auth.LoginScreen
import org.chef.cooksense.auth.SignUpScreen
import org.chef.cooksense.discover.DiscoverScreen
import org.chef.cooksense.discover.DiscoverViewModel


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Discover : Screen("Discover")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
    ) {
        composable(Screen.Login.route) {
            val viewModel = AuthViewModel()
            LoginScreen(
                viewModel = viewModel,
                onLoginSuccess = {
                    navController.navigate(Screen.Discover.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
            )
        }

        composable(Screen.SignUp.route) {
            val viewModel = AuthViewModel()
            SignUpScreen(
                viewModel = viewModel,
                onSignUpSuccess = {
                    navController.navigate(Screen.Discover.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route)
                },
            )
        }
        composable(Screen.Discover.route) {
            val viewModel = DiscoverViewModel()
            DiscoverScreen(
                viewModel = viewModel
            )
        }
    }
}
