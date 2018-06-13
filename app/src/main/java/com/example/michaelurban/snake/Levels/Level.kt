package com.example.michaelurban.snake.Levels

import android.graphics.Point
import com.example.michaelurban.snake.OwnClass.Line
import java.io.Serializable
import java.util.ArrayList

open class Level(val width: Int = 36, val height: Int=26, var lines: ArrayList<Line> = ArrayList()) : Serializable {


    // generate lines for board
    open fun generateLines(spaceH: Int, spaceV: Int, pix: Int, screenWidth: Int, screenHeigh: Int): ArrayList<Line> {
        return lines
    }

    // check collision function
    open fun checkCollision(x: Int, y: Int): Boolean {
        return false
    }

    // generate apple point
    open fun randApple(): Point {
        return Point()
    }

    // generate premium apple point
    open fun randPremium(): Point {
        return Point()
    }
}