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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineUiState
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModelFactory
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun AddWineScreen(onSaved: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(
        factory = WineViewModelFactory(context.applicationContext as Application)
    )
    val state by vm.uiState.collectAsState()

    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Cadastrar Novo Vinho",
            fontSize = 20.sp,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        val campos = listOf(
            "Nome" to state.nome,
            "Tipo" to state.tipo,
            "Ano" to state.ano,
            "Preço" to state.preco,
            "País de origem" to state.paisOrigem,
            "Produtor" to state.produtor,
            "Qtd em estoque" to state.quantidadeEstoque
        )

        campos.forEach { (label, valor) ->
            CampoTextoForm(
                label = label,
                value = valor,
                onValueChange = { vm.updateUi { copyField(label, it) } }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onSaved,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = corVinho,
                    contentColor = Color.White
                )
            ) {
                Text("Voltar", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Button(
                onClick = { vm.salvarVinho(onSaved) },
                enabled = state.isValid,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = corVinho,
                    contentColor = Color.White
                )
            ) {
                Text("Salvar", fontSize = 14.sp)
            }
        }
    }
}
private fun WineUiState.copyField(field: String, value: String): WineUiState = when (field) {
    "Nome" -> copy(nome = value)
    "Tipo" -> copy(tipo = value)
    "Ano" -> copy(ano = value)
    "Preço" -> copy(preco = value)
    "País de origem" -> copy(paisOrigem = value)
    "Produtor" -> copy(produtor = value)
    "Qtd em estoque" -> copy(quantidadeEstoque = value)
    else -> this
}

@Composable
private fun CampoTextoForm(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
        singleLine = true
    )
    Spacer(modifier = Modifier.height(8.dp))
}