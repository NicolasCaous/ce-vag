package br.com.fiap.ideaseeders.cevag.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wine::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wineDao(): WineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "estoque_vinheria_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
