package com.example.autohelper.data.notes.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.autohelper.data.users.room.entities.UserEntity
import java.io.Serializable

@Entity(tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "name_detail") val nameDetail: String,
    val mileage: Int,
    val description: String,
    @ColumnInfo(name = "user_id") val userId: Int
): Serializable