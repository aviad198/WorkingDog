/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.workingdog.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * A com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database that stores TimeTrack information.
 * And a global method to get access to the com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database.
 *
 */
@Database(entities = [TimeTrack::class], version = 1, exportSchema = false)
abstract class ActivityDatabase : RoomDatabase() {

    /**
     * Connects the com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database to the DAO.
     */
    abstract val activityDatabaseDao: ActivityDatabaseDao

    /**
     * Define a companion object, this allows us to add functions on the ActivityDatabase class.
     *
     * For example, clients can call `ActivityDatabase.getInstance(context)` to instantiate
     * a new ActivityDatabase.
     */
    companion object {
        /**
         * INSTANCE will keep a reference to any com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database returned via getInstance.
         *
         * This will help us avoid repeatedly initializing the com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database, which is expensive.
         *
         *  The value of a volatile variable will never be cached, and all writes and
         *  reads will be done to and from the main memory. It means that changes made by one
         *  thread to shared data are visible to other threads.
         */
        @Volatile
        private var INSTANCE: ActivityDatabase? = null

        /**
         * Helper function to get the com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database.
         *
         * If a com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database has already been retrieved, the previous com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database will be returned.
         * Otherwise, create a new com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database.
         *
         * This function is threadsafe, and callers should cache the result for multiple com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database
         * calls to avoid overhead.
         *
         * This is an example of a simple Singleton pattern that takes another Singleton as an
         * argument in Kotlin.
         *
         * To learn more about Singleton read the wikipedia article:
         * https://en.wikipedia.org/wiki/Singleton_pattern
         *
         * @param context The application context Singleton, used to get access to the filesystem.
         */
        fun getInstance(context: Context): ActivityDatabase {
            // Multiple threads can ask for the com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            ActivityDatabase::class.java,
                            "sleep_history_database"
                    )
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this lesson. You can learn more about
                            // migration with Room in this blog post:
                            // https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929
                            .fallbackToDestructiveMigration()
                            .build()
                    // Assign INSTANCE to the newly created com.example.workingdog.com.example.workingdog.activitytracker.activitytracker.database.
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}
