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
fun UpdatePriceScreen(onBack: () -> Unit) {
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
            text = "💰 Atualizar Preço",
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
                var novoPreco by remember { mutableStateOf("") }

                WineActionCard(
                    nome = vinho.nome,
                    ano = vinho.ano,
                    infoSecundaria = "Preço atual: R$ %.2f".format(vinho.preco),
                    labelCampo = "Novo preço",
                    valorCampo = novoPreco,
                    onValueChange = { novoPreco = it },
                    textoBotao = "Atualizar",
                    onConfirmar = {
                        val preco = novoPreco.toDoubleOrNull()
                        if (preco != null && preco > 0.0) {
                            vm.atualizarPreco(vinho.id, preco) {
                                Toast.makeText(context, "Preço atualizado!", Toast.LENGTH_SHORT).show()
                                novoPreco = ""
                                vm.buscar()
                            }
                        } else {
                            Toast.makeText(context, "Preço inválido!", Toast.LENGTH_SHORT).show()
                        }
                    },
                    corCard = corVinho,
                    corTexto = Color.White,
                    enabled = novoPreco.toDoubleOrNull() != null
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
            Text("Voltar")
        }
    }
}
