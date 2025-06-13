package br.com.fiap.ideaseeders.cevag.database

data class WineReport(
    val totalVinhos: Int,
    val totalEstoque: Int,
    val valorTotal: Double,
    val precoMedio: Double,
    val precoMinimo: Double,
    val precoMaximo: Double
)
