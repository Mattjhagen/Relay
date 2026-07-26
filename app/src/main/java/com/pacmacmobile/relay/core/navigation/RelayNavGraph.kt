package com.pacmacmobile.relay.core.navigation

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pacmacmobile.relay.core.design.theme.RelayTheme
import com.pacmacmobile.relay.feature.home.HomeScreen
import com.pacmacmobile.relay.feature.server.ServerScreen
import com.pacmacmobile.relay.feature.settings.SettingsScreen
import com.pacmacmobile.relay.feature.terminal.TerminalScreen

sealed class Screen(val route: String, val title: String) {
    object Home : Screen("home", "Home")
    object Terminal : Screen("terminal", "Terminal")
    object Server : Screen("server", "Server")
    object Settings : Screen("settings", "Settings")
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Terminal,
    Screen.Server,
    Screen.Settings
)

@Composable
fun RelayNavGraph() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BoxWithConstraints {
        val isTablet = maxWidth >= 600.dp

        if (isTablet) {
            Row {
                NavigationRail(
                    containerColor = RelayTheme.colors.surface,
                    contentColor = RelayTheme.colors.textSecondary
                ) {
                    bottomNavItems.forEach { screen ->
                        NavigationRailItem(
                            icon = { Text(text = screen.title.take(1), style = RelayTheme.typography.h3) },
                            label = { Text(text = screen.title, style = RelayTheme.typography.caption) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            colors = NavigationRailItemDefaults.colors(
                                selectedIconColor = RelayTheme.colors.accent,
                                selectedTextColor = RelayTheme.colors.accent,
                                indicatorColor = RelayTheme.colors.surfaceHighlight,
                                unselectedIconColor = RelayTheme.colors.textSecondary,
                                unselectedTextColor = RelayTheme.colors.textSecondary
                            ),
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
                Scaffold { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Home.route) { HomeScreen() }
                        composable(Screen.Terminal.route) { TerminalScreen() }
                        composable(Screen.Server.route) { ServerScreen() }
                        composable(Screen.Settings.route) { SettingsScreen() }
                    }
                }
            }
        } else {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        containerColor = RelayTheme.colors.surface,
                        contentColor = RelayTheme.colors.textSecondary
                    ) {
                        bottomNavItems.forEach { screen ->
                            NavigationBarItem(
                                icon = { Text(text = screen.title.take(1), style = RelayTheme.typography.h3) },
                                label = { Text(text = screen.title, style = RelayTheme.typography.caption) },
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = RelayTheme.colors.accent,
                                    selectedTextColor = RelayTheme.colors.accent,
                                    indicatorColor = RelayTheme.colors.surfaceHighlight,
                                    unselectedIconColor = RelayTheme.colors.textSecondary,
                                    unselectedTextColor = RelayTheme.colors.textSecondary
                                ),
                                onClick = {
                                    navController.navigate(screen.route) {
                                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Home.route) { HomeScreen() }
                    composable(Screen.Terminal.route) { TerminalScreen() }
                    composable(Screen.Server.route) { ServerScreen() }
                    composable(Screen.Settings.route) { SettingsScreen() }
                }
            }
        }
    }
}
