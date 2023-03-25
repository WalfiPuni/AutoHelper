package com.example.autohelper.data.users.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.autohelper.data.users.SignUpData
import java.io.Serializable

@Entity(
    tableName = "users",
    indices = [
        Index("login", unique = true)
    ]
)
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val login: String,
    val password: String,
    val name: String
): Serializable {

    companion object{
        fun fromSignUpData(signUpData: SignUpData) = UserEntity(
            id = null,
            login = signUpData.login,
            password = signUpData.password,
            name = signUpData.name
        )
    }
}