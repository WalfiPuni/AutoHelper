package com.example.autohelper.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.autohelper.data.notes.room.NotesDao
import com.example.autohelper.data.notes.room.entities.NoteEntity
import com.example.autohelper.data.users.room.UsersDao
import com.example.autohelper.data.users.room.entities.UserEntity

@Database(
    entities = [
        UserEntity::class,
        NoteEntity::class
    ],
    version = 1
)
abstract class MainDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NotesDao

    abstract fun getUserDao(): UsersDao

    companion object{
        @Volatile
        private var INSTANCE: MainDatabase? = null
        fun getDatabase(context: Context): MainDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "autohelper.db"
                ).build()
                instance
            }
        }
    }

}