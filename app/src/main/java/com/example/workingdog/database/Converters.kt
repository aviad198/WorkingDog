package com.example.workingdog.database

import androidx.room.TypeConverter
import java.sql.Date
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
    @TypeConverter
    fun fromTimeMillis(millis: Long): Calendar? {
        val cal = Calendar.getInstance()
        cal.timeInMillis = millis

        return cal
    }

    @TypeConverter
    fun toTimeMillis(cal: Calendar): Long? {
        return cal.timeInMillis
    }
}