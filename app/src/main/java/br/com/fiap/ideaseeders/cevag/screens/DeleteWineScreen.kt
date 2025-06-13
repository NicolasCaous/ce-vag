package br.com.fiap.ideaseeders.cevag.screens

import android.app.Application
import android.widget.Toast
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
fun DeleteWineScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))

    val pesquisa by vm.pesquisa.collectAsState()
    val resultados by vm.resultadoBusca.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(24.dp)
    ) {
        Text(
            text = "🗑️ Excluir Vinho",
            fontSize = 20.sp,
            color = corVinho,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pesquisa,
            onValueChange = {
                vm.atualizarPesquisa(it)
                vm.buscar()
            },
            label = { Text("Buscar vinho por nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(resultados) { vinho ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = corVinho)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("${vinho.nome} (${vinho.ano})", color = Color.White)
                        Text("Tipo: ${vinho.tipo} • Estoque: ${vinho.quantidadeEstoque}", color = Color.White)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                vm.excluirVinho(vinho) {
                                    Toast.makeText(context, "Vinho excluído com sucesso!", Toast.LENGTH_SHORT).show()
                                    vm.buscar()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = corVinho
                            ),
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

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
