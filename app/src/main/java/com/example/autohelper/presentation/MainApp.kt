package com.example.autohelper.presentation

import android.app.Application
import com.example.autohelper.data.db.MainDatabase

class MainApp: Application() {
    val database by lazy { MainDatabase.getDatabase(this)}
}