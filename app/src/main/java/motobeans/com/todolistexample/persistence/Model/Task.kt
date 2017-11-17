package motobeans.com.todolistexample.persistence.Model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.UUID

/**
 * Created by munishkumarthakur on 17/11/17.
 */

@Entity(tableName = "task")
data class Task(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var taskDesc: String,
    var isActive: Boolean = true
)