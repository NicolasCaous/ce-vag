package br.com.fiap.ideaseeders.cevag.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModelFactory

@Composable
fun ReportScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(factory = WineViewModelFactory(context.applicationContext as Application))
    val relatorio by vm.relatorio.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    LaunchedEffect(Unit) {
        vm.carregarRelatorio()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "📊 Relatório Geral",
            fontSize = 20.sp,
            color = corVinho,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(0.95f),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(0.dp)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(corVinho)
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("MÉTRICA", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("VALOR", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Divider(color = corVinho, thickness = 1.dp)

                relatorio?.let { r ->
                    TabelaLinha("Total de vinhos cadastrados", r.totalVinhos.toString(), corVinho)
                    TabelaLinha("Total de unidades em estoque", r.totalEstoque.toString(), corVinho)
                    TabelaLinha("Valor total estimado", "R$ %.2f".format(r.valorTotal), corVinho)
                    TabelaLinha("Preço médio dos vinhos", "R$ %.2f".format(r.precoMedio), corVinho)
                    TabelaLinha("Preço mais barato", "R$ %.2f".format(r.precoMinimo), corVinho)
                    TabelaLinha("Preço mais caro", "R$ %.2f".format(r.precoMaximo), corVinho)
                } ?: run {
                    Text(
                        "Carregando dados...",
                        modifier = Modifier.padding(16.dp),
                        color = corVinho
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = corVinho,
                contentColor = Color.White
            )
        ) {
            Text("Voltar", fontSize = 14.sp)
        }
    }
}

@Composable
fun TabelaLinha(label: String, valor: String, corTexto: Color) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, color = corTexto, fontSize = 14.sp)
            Text(valor, color = corTexto, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }
        Divider(color = corTexto.copy(alpha = 0.2f))
    }
}

