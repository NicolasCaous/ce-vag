package br.com.fiap.ideaseeders.cevag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.ideaseeders.cevag.screens.MainMenuScreen
import br.com.fiap.ideaseeders.cevag.screens.AddWineScreen
import br.com.fiap.ideaseeders.cevag.navigation.NavRoutes
import br.com.fiap.ideaseeders.cevag.screens.DeleteWineScreen
import br.com.fiap.ideaseeders.cevag.screens.ListWinesScreen
import br.com.fiap.ideaseeders.cevag.screens.RegisterSaleScreen
import br.com.fiap.ideaseeders.cevag.screens.ReportScreen
import br.com.fiap.ideaseeders.cevag.screens.RestockWineScreen
import br.com.fiap.ideaseeders.cevag.screens.SearchWineScreen
import br.com.fiap.ideaseeders.cevag.screens.UpdatePriceScreen
import br.com.fiap.ideaseeders.cevag.ui.theme.CEVAGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CEVAGTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.MENU
                ) {
                    composable(NavRoutes.MENU) {
                        MainMenuScreen(
                            onNavigate = { selectedOption ->
                                when (selectedOption) {
                                    1 -> navController.navigate(NavRoutes.LIST_WINES)
                                    2 -> navController.navigate(NavRoutes.ADD_WINE)
                                    3 -> navController.navigate(NavRoutes.RESTOCK)
                                    4 -> navController.navigate(NavRoutes.REGISTER_SALE)
                                    5 -> navController.navigate(NavRoutes.REPORT)
                                    6 -> navController.navigate(NavRoutes.SEARCH)
                                    7 -> navController.navigate(NavRoutes.DELETE)
                                    8 -> navController.navigate(NavRoutes.UPDATE_PRICE)
                                }
                            }
                        )
                    }

                    composable(NavRoutes.ADD_WINE) {
                        AddWineScreen(
                            onSaved = { navController.popBackStack() } // volta ao menu após salvar
                        )
                    }

                    composable(NavRoutes.LIST_WINES) {
                        ListWinesScreen(onBack = { navController.popBackStack() })
                    }

                    composable(NavRoutes.REPORT) {
                        ReportScreen(onBack = { navController.popBackStack() })
                    }

                    composable(NavRoutes.SEARCH) {
                        SearchWineScreen(onBack = { navController.popBackStack() })
                    }

                    composable(NavRoutes.RESTOCK) {
                        RestockWineScreen(
                            onBack = { navController.navigate(NavRoutes.MENU) }
                        )
                    }

                    composable(NavRoutes.REGISTER_SALE) {
                        RegisterSaleScreen(onBack = { navController.navigate(NavRoutes.MENU) })
                    }

                    composable(NavRoutes.DELETE) {
                        DeleteWineScreen(onBack = { navController.navigate(NavRoutes.MENU) })
                    }

                    composable(NavRoutes.UPDATE_PRICE) {
                        UpdatePriceScreen(onBack = { navController.navigate(NavRoutes.MENU) })
                    }


                }
            }
        }
    }
}

