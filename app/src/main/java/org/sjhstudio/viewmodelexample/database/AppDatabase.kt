package org.sjhstudio.viewmodelexample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Example::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun exampleDao(): ExampleDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if(instance == null) {
                // 다중스레드에서 한번의 접근을 위해 방지
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "db"
                    ).build()
                }
            }

            return instance
        }
    }

}