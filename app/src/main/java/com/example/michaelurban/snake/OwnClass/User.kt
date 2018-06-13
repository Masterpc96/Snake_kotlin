package com.example.michaelurban.snake.OwnClass

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "USERS")
class User {
    @PrimaryKey
    var nick: String = ""

    var points: Int = 0
}
