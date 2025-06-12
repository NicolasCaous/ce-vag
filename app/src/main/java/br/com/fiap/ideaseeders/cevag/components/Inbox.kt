package br.com.fiap.ideaseeders.cevag.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inbox(
    label: String,
    placeHolder: String,
    value: String,
    keyboardType: KeyboardType,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        modifier = modifier,
        label = {
            Text(text=label)
        },
        placeholder = {
            Text(text=placeHolder)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}