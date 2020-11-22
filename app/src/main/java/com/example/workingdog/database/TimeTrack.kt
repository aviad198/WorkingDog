package com.example.workingdog.database

import androidx.room.*
import java.util.*


@Entity(tableName = "daily_activity_time_table")
data class TimeTrack(

//        @PrimaryKey
//        val id: Int,
//        val startTime: Long,
//        val endTime: Long,
//        val sleepQuality: Int = -1
//)
//
//{
//        val totalTime: Long
//                get() = endTime - startTime
//
//        // maybe this as well, gets the Local Date as LocalDate in case you want to filter by that
//        val startDate: LocalDate
//                get() = Instant.ofEpochSecond(startTime)
//}
        @PrimaryKey(autoGenerate = true)
        var activityId: Long = 0L,

        @ColumnInfo(name = "start_time")

        val startTimeMilli: Calendar? = Calendar.getInstance(),

        @ColumnInfo(name = "end_time")
        var endTimeMilli: Calendar? = startTimeMilli,

        @ColumnInfo(name = "quality_rating")
        var sleepQuality: Int = -1
){
        override fun toString(): String {
                return "${startTimeMilli.toString()} ${endTimeMilli.toString()}"
        }
}