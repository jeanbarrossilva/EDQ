package com.jeanbarrossilva.realism.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.jeanbarrossilva.realism.MainActivity
import com.jeanbarrossilva.realism.ui.component.RealismScaffold
import com.jeanbarrossilva.realism.ui.default.RealismScreen
import com.jeanbarrossilva.realism.ui.default.RealismTheme

@Composable
fun MainUI(activity: MainActivity) {
    val navController = rememberNavController()
    val (isFabVisible, setFabVisible) = remember { mutableStateOf(true) }

    RealismTheme.OnSurface {
        RealismScaffold(isFabVisible, onFabClick = { navController.navigate("preferences") }) {
            NavHost(navController, startDestination = "quotes") {
                RealismScreen.values.forEach { screen ->
                    composable(screen.route) {
                        screen.ui()
                        setFabVisible(screen.showsFab)
                    }
                }
            }
        }
    }

    activity.viewModel.configNotificationChannel()
}