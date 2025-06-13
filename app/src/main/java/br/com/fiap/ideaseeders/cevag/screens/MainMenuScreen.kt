package br.com.fiap.ideaseeders.cevag.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.ideaseeders.cevag.R

@Composable
fun MainMenuScreen(
    onNavigate: (Int) -> Unit
) {
    // Cores personalizadas
    val corVinho = Color(0xFF400724)
    val corCreme = Color(0xFFFFF8E1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(corCreme)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top)
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.slogan),
            contentDescription = "Logo Vinheria Agnello",
            modifier = Modifier
                .height(120.dp)
                .padding(bottom = 8.dp)
        )

        val menuOptions = listOf(
            "1. Listar todos os vinhos",
            "2. Cadastrar novo vinho",
            "3. Repor estoque",
            "4. Registrar venda",
            "5. Emitir relatório completo",
            "6. Pesquisar vinho",
            "7. Excluir vinho",
            "8. Atualizar preço"
        )

        // Reduz a altura e espaçamento para caber tudo
        menuOptions.forEachIndexed { index, label ->
            Button(
                onClick = { onNavigate(index + 1) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = corVinho,
                    contentColor = Color.White
                )
            ) {
                Text(text = label, fontSize = 14.sp)
            }
        }
    }
}
