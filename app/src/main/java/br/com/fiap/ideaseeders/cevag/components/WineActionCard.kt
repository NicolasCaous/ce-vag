package br.com.fiap.ideaseeders.cevag.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WineActionCard(
    nome: String,
    ano: Int,
    infoSecundaria: String,
    labelCampo: String,
    valorCampo: String,
    onValueChange: (String) -> Unit,
    textoBotao: String,
    onConfirmar: () -> Unit,
    corCard: Color,
    corTexto: Color,
    enabled: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = corCard)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("$nome ($ano)", color = corTexto)
            Text(infoSecundaria, color = corTexto)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = valorCampo,
                onValueChange = onValueChange,
                label = { Text(labelCampo) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = LocalTextStyle.current.copy(color = Color.White),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onConfirmar,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = corCard
                ),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(textoBotao)
            }
        }
    }
}


