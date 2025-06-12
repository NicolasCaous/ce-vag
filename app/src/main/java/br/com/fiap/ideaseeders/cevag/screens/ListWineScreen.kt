package br.com.fiap.ideaseeders.cevag.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.ideaseeders.cevag.database.Wine
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModelFactory
import android.app.Application
import androidx.compose.ui.platform.LocalContext

@Composable
fun ListWinesScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))
    val wineList by vm.wineList.collectAsState()

    LaunchedEffect(Unit) {
        vm.carregarVinhos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text("Lista de Vinhos", style = MaterialTheme.typography.headlineSmall)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = onBack) {
                Text("Voltar ao menu")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(wineList) { vinho ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("🍷 ${vinho.nome} (${vinho.ano}) - ${vinho.tipo}")
                        Text("País: ${vinho.paisOrigem} • Produtor: ${vinho.produtor}")
                        Text("Estoque: ${vinho.quantidadeEstoque} • Preço: R$ ${"%.2f".format(vinho.preco)}")
                    }
                }
            }
        }
    }
}
