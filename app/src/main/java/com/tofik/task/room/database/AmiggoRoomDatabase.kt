package com.tekzee.amiggos.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tekzee.amiggos.room.dao.transactionDao
import com.tekzee.amiggos.room.entity.Notes

@Database(entities = [Notes::class], version = 1, exportSchema = false)
public abstract class AmiggoRoomDatabase : RoomDatabase() {

   abstract fun transactionDao(): transactionDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time. 
        @Volatile
        private var INSTANCE: AmiggoRoomDatabase? = null

        fun getDatabase(context: Context): AmiggoRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AmiggoRoomDatabase::class.java,
                        "atm_database"
                    ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
   }
}