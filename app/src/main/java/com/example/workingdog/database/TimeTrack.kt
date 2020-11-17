package com.example.workingdog.database

import androidx.room.*
import com.example.workingdog.convertLongToDateOnlyString
import com.example.workingdog.convertLongToDateString
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@Entity(tableName = "daily_activity_time_table")
data class TimeTrack(


        @PrimaryKey(autoGenerate = true)
        var activityId: Long = 0L,

//        @ColumnInfo(name = "start_time")
//        @TypeConverters(Converters::class)
//        val startTimeMilli: Date? = System.currentTimeMillis(),

        @ColumnInfo(name = "start_time")
        val startTimeMilli: Date = Date(System.currentTimeMillis()),

        @ColumnInfo(name = "end_time")
        var endTimeMilli: Date = startTimeMilli,

        @ColumnInfo(name = "quality_rating")
        var sleepQuality: Int = -1
)