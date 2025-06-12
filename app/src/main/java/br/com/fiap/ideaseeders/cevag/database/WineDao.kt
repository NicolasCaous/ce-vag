package br.com.fiap.ideaseeders.cevag.database

import androidx.room.*

@Dao
interface WineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(wine: Wine)

    @Update
    suspend fun atualizar(wine: Wine)

    @Delete
    suspend fun deletar(wine: Wine)

    @Query("SELECT * FROM wines")
    suspend fun listarTodos(): List<Wine>

    @Query("SELECT * FROM wines WHERE nome LIKE '%' || :nome || '%'")
    suspend fun pesquisarPorNome(nome: String): List<Wine>

    @Query("SELECT * FROM wines WHERE id = :id")
    suspend fun buscarPorId(id: Int): Wine?
}
