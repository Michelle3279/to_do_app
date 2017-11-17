package motobeans.com.todolistexample.persistence.DB

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import motobeans.com.todolistexample.persistence.DAO.TaskDao
import motobeans.com.todolistexample.persistence.Model.Task

/**
 * Created by munishkumarthakur on 17/11/17.
 */
@Database(entities = arrayOf(Task::class), version = 1)
abstract class TaskDatabase : RoomDatabase() {
  abstract fun taskDao(): TaskDao

  companion object {

    @Volatile private var INSTANCE: TaskDatabase? = null

    fun getInstance(context: Context): TaskDatabase =
        INSTANCE ?: synchronized(this) {
          INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

    private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
            TaskDatabase::class.java, "Sample.db")
            .build()
  }
}