package br.com.fiap.ideaseeders.cevag.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wines")
data class Wine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val tipo: String,
    val ano: Int,
    val preco: Double,
    val paisOrigem: String,
    val produtor: String,
    val quantidadeEstoque: Int
)
