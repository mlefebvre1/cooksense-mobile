package org.chef.cooksense

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gitlive.firebase.firestore.FirebaseFirestore
import org.chef.cooksense.auth.AuthViewModel
import org.chef.cooksense.auth.LoginScreen
import org.chef.cooksense.auth.SignUpScreen
import org.chef.cooksense.discover.DiscoverScreen
import org.chef.cooksense.discover.DiscoverViewModel
import org.chef.cooksense.favorite.FavoriteScreen


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Discover : Screen("Discover")
}

@Composable
fun AppNavigation(repository: FirebaseFirestore) {
    val navController = rememberNavController()

    // screens that should show the bottom nav
    val bottomNavRoutes = listOf(
        BottomNavScreen.Discover.route,
        BottomNavScreen.Favourites.route,
        BottomNavScreen.Profile.route
    )

    Scaffold(
        bottomBar = {
            val currentBackStack by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStack?.destination?.route
            println("Current route: $currentRoute")

            if (currentRoute in bottomNavRoutes) {
                BottomNavigationBar(
                    currentRoute = currentRoute ?: BottomNavScreen.Discover.route,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            // avoid building up a large back stack
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            val discoverViewModel = DiscoverViewModel(repository = repository)

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
                DiscoverScreen(
                    viewModel = discoverViewModel
                )
            }
            composable(BottomNavScreen.Favourites.route) {
                FavoriteScreen(discoverViewModel)
            }

            composable(BottomNavScreen.Profile.route) {
                //TODO:
//                ProfileScreen()
            }
        }
    }
}
