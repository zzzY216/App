package com.software.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.software.app.data.local.mongo.bili.BiliSessionManager
import com.software.app.ui.MainHomeScreen
import com.software.app.ui.blbl.bilidetail.BiliDetailScreen
import com.software.app.ui.blbl.bilihome.BiliHomeUiScreen
import com.software.app.ui.blbl.bililogin.BiliLoginUiScreen
import com.software.app.ui.blbl.blblrecommend.BiliRecommendUiScreen
import com.software.app.ui.fit.fitnote.FitUiNoteScreen
import com.software.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val sessionManager = BiliSessionManager(this)
                RootNavHost(
                    sessionManager = sessionManager
                )
            }
        }
    }
}

@Composable
fun RootNavHost(
    sessionManager: BiliSessionManager
) {
    val rootNavController = rememberNavController()
    NavHost(navController = rootNavController, startDestination = RouteMainAppEntry) {
        composable<RouteMainAppEntry> {
            MainContainer(
                onOpenFitApp = {
                    rootNavController.navigate(RouteFitAppEntry)
                },
                onOpenBiliApp = {
                    rootNavController.navigate(RouteBiliAppEntry)
                }
            )
        }
        composable<RouteFitAppEntry> {
            FitAppContainer(
                onExitApp = {
                    rootNavController.navigateUp()
                }
            )
        }
        composable<RouteBiliAppEntry> {
            BiliAppContainer(
                onExitApp = {
                    rootNavController.navigateUp()
                },
                sessionManager = sessionManager
            )
        }
    }
}

@Composable
fun MainContainer(
    onOpenFitApp: () -> Unit,
    onOpenBiliApp: () -> Unit
) {
    val mainNavController = rememberNavController()
    val mainBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val mainCurrentDestination = mainBackStackEntry?.destination
    val showMainBottomBar = mainCurrentDestination?.hasRoute<RouteMainHome>() == true
    val bottomLevelRoute = listOf(
        TopLevelRoute(
            name = "Home",
            route = RouteMainHome,
            icon = R.drawable.ic_home
        ),
        TopLevelRoute(
            name = "Profile",
            route = RouteMainHome,
            icon = R.drawable.ic_profile
        ),
        TopLevelRoute(
            name = "Fit",
            route = RouteFitAppEntry,
            icon = R.drawable.ic_fit
        ),
        TopLevelRoute(
            name = "Bili",
            route = RouteBiliAppEntry,
            icon = R.drawable.ic_bili
        )
    )

    Column() {
        NavHost(
            navController = mainNavController,
            startDestination = RouteMainHome,
            modifier = Modifier.weight(1f)
        ) {
            composable<RouteMainHome> {
                MainHomeScreen(
                    onNavigateToFit = onOpenFitApp,
                    onNavigateToBili = onOpenBiliApp
                )
            }
        }
        AnimatedContent(
            targetState = showMainBottomBar,
        ) { showBottom ->
            if (showBottom) {
                NavigationBar {
                    bottomLevelRoute.forEach { topLevelRoutes ->
                        val isSelected =
                            mainCurrentDestination?.hasRoute(topLevelRoutes.route::class)
                        NavigationBarItem(
                            selected = isSelected == true,
                            onClick = {
                                mainNavController.navigate(topLevelRoutes.route) {
                                    popUpTo(mainNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(topLevelRoutes.icon),
                                    contentDescription = topLevelRoutes.name,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(text = topLevelRoutes.name)
                            },
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BiliAppContainer(
    sessionManager: BiliSessionManager,
    onExitApp: () -> Unit
) {
    val biliNavController = rememberNavController()
    val biliBackStackEntry by biliNavController.currentBackStackEntryAsState()
    val biliCurrentDestination = biliBackStackEntry?.destination
    val showBiliBottomBar = biliCurrentDestination?.hasRoute<RouteBiliHome>() == true ||
            biliCurrentDestination?.hasRoute<RouteBiliLogin>() == true ||
            biliCurrentDestination?.hasRoute<RouteBiliRecommend>() == true
    val biliBottomBar = listOf(
        TopLevelRoute(
            name = "Home",
            route = RouteBiliHome,
            icon = R.drawable.ic_home
        ),
        TopLevelRoute(
            name = "Dynamic",
            route = RouteBiliDynamic,
            icon = R.drawable.ic_dynamic
        ),
        TopLevelRoute(
            name = "recommend",
            route = RouteBiliRecommend,
            icon = R.drawable.ic_recommend
        ),
        TopLevelRoute(
            name = "Profile",
            route = RouteBiliProfile,
            icon = R.drawable.ic_profile
        )
    )

    Column() {
        NavHost(
            navController = biliNavController, startDestination = RouteBiliLogin,
            modifier = Modifier.weight(1f)
        ) {
            composable<RouteBiliLogin> {
                BiliLoginUiScreen(
//                    onNavigateToRecommend = {
//                        biliNavController.navigate(RouteBiliHome) {
//                            popUpTo<RouteBiliLogin> { inclusive = true }
//                        }
//                    }
                    onNavigateToRecommend = {
                        biliNavController.navigate(RouteBiliRecommend) {
                            popUpTo<RouteBiliLogin> {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable<RouteBiliHome> {
                BiliHomeUiScreen()
            }
            composable<RouteBiliRecommend> {
                BiliRecommendUiScreen(
                    onNavigateToDetail = { avid, cid, qn, type, platform ->
                        biliNavController.navigate(RouteBiliDetail(avid, cid, qn, type, platform))
                    }
                )
            }
            composable<RouteBiliDetail> {
                val route = biliBackStackEntry?.toRoute<RouteBiliDetail>() ?: return@composable
                BiliDetailScreen(
                    route.avid,
                    route.cid,
                    route.qn,
                    route.type,
                    route.platform
                )
            }
        }
        if (showBiliBottomBar) {
            NavigationBar {
                biliBottomBar.forEach { topLevelRoute ->
                    val isSelected = biliCurrentDestination.hasRoute(topLevelRoute.route::class)
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            biliNavController.navigate(topLevelRoute.route) {
                                popUpTo(biliNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                painter = painterResource(topLevelRoute.icon),
                                contentDescription = topLevelRoute.name,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(text = topLevelRoute.name)
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    }
}


@Composable
fun FitAppContainer(onExitApp: () -> Unit) {
    val fitNavController = rememberNavController()
    val fitNavBackStackEntry by fitNavController.currentBackStackEntryAsState()
    val fitCurrentDestination = fitNavBackStackEntry?.destination
    val showFitBottomBar = fitCurrentDestination?.hasRoute<RouteFitNote>() == true
    val fitBottomLevelRoute = listOf(
        TopLevelRoute(
            name = "Note",
            route = RouteFitNote,
            icon = R.drawable.ic_fit
        )
    )
    Column() {
        NavHost(navController = fitNavController, startDestination = RouteFitNote) {
            composable<RouteFitNote> {
                FitUiNoteScreen()
            }
        }
        AnimatedContent(
            targetState = showFitBottomBar
        ) { showBottom ->
            if (showBottom) {
                NavigationBar {
                    fitBottomLevelRoute.forEach { topLevelRoutes ->
                        val isSelected =
                            fitCurrentDestination?.hasRoute(topLevelRoutes.route::class)
                        NavigationBarItem(
                            selected = isSelected == true,
                            onClick = {
                                fitNavController.navigate(topLevelRoutes.route) {
                                    popUpTo(fitNavController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(topLevelRoutes.icon),
                                    contentDescription = topLevelRoutes.name,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(text = topLevelRoutes.name)
                            },
                            alwaysShowLabel = false
                        )
                    }
                }
            }
        }
    }
}


data class TopLevelRoute<T>(val name: String, val route: T, val icon: Int)