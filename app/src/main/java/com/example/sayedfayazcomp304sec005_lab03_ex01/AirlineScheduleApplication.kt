package com.example.sayedfayazcomp304sec005_lab03_ex01

import android.app.Application
import com.example.sayedfayazcomp304sec005_lab03_ex01.database.AppDatabase

class AirlineScheduleApplication : Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}