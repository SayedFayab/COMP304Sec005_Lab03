package com.example.sayedfayazcomp304sec005_lab03_ex01.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sayedfayazcomp304sec005_lab03_ex01.database.schedule.Schedule
import com.example.sayedfayazcomp304sec005_lab03_ex01.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class AirlineViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    fun scheduleForAirlineName(name: String): Flow<List<Schedule>> = scheduleDao.getByAirlineName(name)
}

class AirlineViewModelFactory(
    private val scheduleDao: ScheduleDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AirlineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AirlineViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}