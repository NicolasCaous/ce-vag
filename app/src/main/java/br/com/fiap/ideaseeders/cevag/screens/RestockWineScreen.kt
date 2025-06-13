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
import br.com.fiap.ideaseeders.cevag.components.WineActionCard

@Composable
fun RestockWineScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val viewModel: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))

    val pesquisa by viewModel.pesquisa.collectAsState()
    val resultados by viewModel.resultadoBusca.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(24.dp)
    ) {
        Text(
            text = "📦 Repor Estoque",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall,
            color = corVinho,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = pesquisa,
            onValueChange = {
                viewModel.atualizarPesquisa(it)
                viewModel.buscar()
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
                var novaQtd by remember { mutableStateOf("") }

                WineActionCard(
                    nome = vinho.nome,
                    ano = vinho.ano,
                    infoSecundaria = "Estoque atual: ${vinho.quantidadeEstoque}",
                    labelCampo = "Qtd a adicionar",
                    valorCampo = novaQtd,
                    onValueChange = { novaQtd = it },
                    textoBotao = "Confirmar",
                    onConfirmar = {
                        val qtd = novaQtd.toIntOrNull()
                        if (qtd != null) {
                            viewModel.reporEstoque(vinho.id, qtd) {
                                Toast.makeText(context, "Estoque atualizado com sucesso!", Toast.LENGTH_SHORT).show()
                                novaQtd = ""
                                viewModel.buscar()
                            }
                        }
                    },
                    corCard = corVinho,
                    corTexto = Color.White,
                    enabled = novaQtd.toIntOrNull() != null
                )
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
            Text("Voltar ao menu")
        }
    }
}
