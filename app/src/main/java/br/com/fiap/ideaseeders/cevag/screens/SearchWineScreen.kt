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
fun SearchWineScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))

    val termo by vm.pesquisa.collectAsState()
    val resultados by vm.resultadoBusca.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "🔍 Pesquisar Vinho",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp,
            color = corVinho
        )

        OutlinedTextField(
            value = termo,
            onValueChange = {
                vm.atualizarPesquisa(it)
                vm.buscar()
            },
            label = { Text("Digite o nome do vinho") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(resultados) { vinho ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = corVinho)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${vinho.nome} (${vinho.ano}) - ${vinho.tipo}", color = Color.White)
                        Text("Estoque: ${vinho.quantidadeEstoque} • Preço: R$ %.2f".format(vinho.preco), color = Color.White)
                    }
                }
            }
        }

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = corVinho,
                contentColor = Color.White
            )
        ) {
            Text("Voltar")
        }
    }
}
