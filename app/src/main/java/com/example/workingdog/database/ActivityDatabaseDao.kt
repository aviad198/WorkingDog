package com.example.workingdog.database

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.sql.Date
import java.util.*

/**
 * Defines methods for using the TimeTrack class with Room.
 */

@Dao
interface ActivityDatabaseDao {

    @Insert
    suspend fun insert(activity: TimeTrack)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param activity new value to write
     */
    @Update
    suspend fun update(activity: TimeTrack)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from daily_activity_time_table WHERE activityId = :key")
    suspend fun get(key: Long): TimeTrack?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM daily_activity_time_table")
     suspend fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM daily_activity_time_table ORDER BY activityId DESC")
    fun getAllActivities(): LiveData<List<TimeTrack>>

    /**
     * Selects and returns the latest day.
     */
    @Query("SELECT * FROM daily_activity_time_table ORDER BY activityId DESC LIMIT 1")
    suspend fun getToday(): TimeTrack?



//    @SuppressLint("SimpleDateFormat")
//    fun convertLongToDateOnlyString(systemTime: Long): java.util.Date {
//        return java.util.Date(systemTime)
//    }

//    @Query("SELECT datetime(start_time, 'unixepoch') FROM daily_activity_time_table ORDER BY activityId DESC LIMIT 1")
//    suspend fun getTodayDate(): TimeTrack?

//    @Query("SELECT SUM(end_time-start_time) from daily_activity_time_table where start_time > date('now') ")
//    fun getTodayRecord(): Long

    @Query("SELECT SUM(end_time-start_time) FROM daily_activity_time_table WHERE start_time BETWEEN :from AND :to")
    suspend fun getAllByDate(from: Calendar,to: Calendar): Double

}