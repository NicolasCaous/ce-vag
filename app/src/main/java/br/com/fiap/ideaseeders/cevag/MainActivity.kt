package br.com.fiap.ideaseeders.cevag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.ideaseeders.cevag.screens.MainMenuScreen
import br.com.fiap.ideaseeders.cevag.screens.AddWineScreen
import br.com.fiap.ideaseeders.cevag.navigation.NavRoutes
import br.com.fiap.ideaseeders.cevag.screens.ListWinesScreen
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
                }
            }
        }
    }
}

