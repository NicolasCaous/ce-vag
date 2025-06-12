package br.com.fiap.ideaseeders.cevag.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.ideaseeders.cevag.database.AppDatabase
import br.com.fiap.ideaseeders.cevag.database.Wine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

data class WineUiState(
    val nome: String = "",
    val tipo: String = "",
    val ano: String = "",
    val preco: String = "",
    val paisOrigem: String = "",
    val produtor: String = "",
    val quantidadeEstoque: String = "",
    val isValid: Boolean = false
)

class WineViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = AppDatabase.getDatabase(app).wineDao()

    private val _uiState = MutableStateFlow(WineUiState())
    val uiState: StateFlow<WineUiState> = _uiState

    fun updateUi(update: WineUiState.() -> WineUiState) {
        _uiState.value = _uiState.value.update()
            .let { it.copy(isValid = validate(it)) }
    }

    private fun validate(s: WineUiState) =
        s.nome.isNotBlank() && s.tipo.isNotBlank() && s.ano.toIntOrNull() != null &&
                s.preco.toDoubleOrNull() != null && s.paisOrigem.isNotBlank() &&
                s.produtor.isNotBlank() && s.quantidadeEstoque.toIntOrNull() != null

    fun save() = viewModelScope.launch {
        val s = _uiState.value
        dao.inserir(
            Wine(
                nome = s.nome,
                tipo = s.tipo,
                ano = s.ano.toInt(),
                preco = s.preco.toDouble(),
                paisOrigem = s.paisOrigem,
                produtor = s.produtor,
                quantidadeEstoque = s.quantidadeEstoque.toInt()
            )
        )
        _uiState.value = WineUiState()
    }

    fun salvarVinho(onSaved: () -> Unit) {
        viewModelScope.launch {
            val vinho = Wine(
                nome = uiState.value.nome,
                tipo = uiState.value.tipo,
                ano = uiState.value.ano.toIntOrNull() ?: 0,
                preco = uiState.value.preco.toDoubleOrNull() ?: 0.0,
                paisOrigem = uiState.value.paisOrigem,
                produtor = uiState.value.produtor,
                quantidadeEstoque = uiState.value.quantidadeEstoque.toIntOrNull() ?: 0
            )

            dao.inserir(vinho)
            Toast.makeText(getApplication(), "Vinho salvo com sucesso!", Toast.LENGTH_SHORT).show()
            onSaved()
        }
    }

    private val _wineList = MutableStateFlow<List<Wine>>(emptyList())
    val wineList: StateFlow<List<Wine>> = _wineList

    fun carregarVinhos() {
        viewModelScope.launch {
            _wineList.value = dao.listarTodos()
        }
    }


}
