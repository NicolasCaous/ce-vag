package br.com.fiap.ideaseeders.cevag.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModelFactory

@Composable
fun ListWinesScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))
    val wineList by vm.wineList.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    LaunchedEffect(Unit) {
        vm.carregarVinhos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de Vinhos",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall,
            color = corVinho
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(wineList) { vinho ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = corVinho),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                    ) {
                        Text(
                            "🍷 ${vinho.nome} (${vinho.ano}) - ${vinho.tipo}",
                            fontSize = 14.sp,
                            color = corCreme
                        )
                        Text(
                            "País: ${vinho.paisOrigem} • Produtor: ${vinho.produtor}",
                            fontSize = 13.sp,
                            color = corCreme
                        )
                        Text(
                            "Estoque: ${vinho.quantidadeEstoque} • Preço: R$ %.2f".format(vinho.preco),
                            fontSize = 13.sp,
                            color = corCreme
                        )
                    }
                }
            }
        }

        Button(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.End)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = corVinho,
                contentColor = Color.White
            )
        ) {
            Text("Voltar", fontSize = 14.sp)
        }
    }
}
