package br.com.fiap.ideaseeders.cevag.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(
    onNavigate: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text(
            text = "Estoque Vinheria Agnello",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineSmall
        )

        val menuOptions = listOf(
            "1. Listar todos os vinhos",
            "2. Cadastrar novo vinho",
            "3. Repor estoque",
            "4. Registrar venda",
            "5. Emitir relatório completo",
            "6. Pesquisar vinho",
            "7. Excluir vinho",
            "8. Atualizar preço",
            "0. Sair"
        )

        menuOptions.forEachIndexed { index, label ->
            Button(
                onClick = { onNavigate(index + 1) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(label)
            }
        }
    }
}
