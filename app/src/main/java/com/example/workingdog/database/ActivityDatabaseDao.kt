package com.example.workingdog.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * Defines methods for using the TimeTrack class with Room.
 */

@Dao
interface ActivityDatabaseDao {
    @Insert
     fun insert(activity: TimeTrack)

    /**
     * When updating a row with a value already set in a column,
     * replaces the old value with the new one.
     *
     * @param activity new value to write
     */
    @Update
     fun update(activity: TimeTrack)

    /**
     * Selects and returns the row that matches the supplied start time, which is our key.
     *
     * @param key startTimeMilli to match
     */
    @Query("SELECT * from daily_activity_time_table WHERE date = :key")
     fun get(key: Long): TimeTrack?

    /**
     * Deletes all values from the table.
     *
     * This does not delete the table, only its contents.
     */
    @Query("DELETE FROM daily_activity_time_table")
     fun clear()

    /**
     * Selects and returns all rows in the table,
     *
     * sorted by start time in descending order.
     */
    @Query("SELECT * FROM daily_activity_time_table ORDER BY date DESC")
    fun getAllActivities(): LiveData<List<TimeTrack>>

    /**
     * Selects and returns the latest day.
     */
    @Query("SELECT * FROM daily_activity_time_table ORDER BY date DESC LIMIT 1")
     fun getDay(): TimeTrack?
}