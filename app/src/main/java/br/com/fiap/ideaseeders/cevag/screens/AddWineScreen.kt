package br.com.fiap.ideaseeders.cevag.screens

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineUiState
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModel
import br.com.fiap.ideaseeders.cevag.viewmodel.WineViewModelFactory

@Composable
fun AddWineScreen(onSaved: () -> Unit) {
    val context = LocalContext.current
    val vm: WineViewModel = viewModel(
        factory = WineViewModelFactory(context.applicationContext as Application)
    )
    val state by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Cadastrar Novo Vinho", modifier = Modifier.padding(bottom = 8.dp))

        val fields = listOf(
            "Nome" to state.nome,
            "Tipo" to state.tipo,
            "Ano" to state.ano,
            "Preço" to state.preco,
            "País de origem" to state.paisOrigem,
            "Produtor" to state.produtor,
            "Qtd em estoque" to state.quantidadeEstoque
        )

        fields.forEach { (label, value) ->
            OutlinedTextField(
                value = value,
                onValueChange = { vm.updateUi { copyField(label, it) } },
                label = { Text(label) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                vm.salvarVinho(onSaved)
            },
            enabled = state.isValid,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Salvar")
        }
    }
}

// Função de extensão para atualizar campos
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
