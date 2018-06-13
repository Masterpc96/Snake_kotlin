package com.example.michaelurban.snake.GameEngine

import android.graphics.Point
import android.util.Log
import com.example.michaelurban.snake.Enums.Direction
import com.example.michaelurban.snake.Enums.States
import com.example.michaelurban.snake.Levels.Level
import java.util.ArrayList
import kotlin.math.log

class GameEngine(val map: Level?) {

    var length: Int = 5

    var premiumPoints: Int = 0

    var height = 0

    var width = 0

    var body: ArrayList<Point>

    var apple = Point()

    var premium: Point? =null

    var currentState = States.Running

    init {
        this.width = map!!.width
        this.height = map.height
        body = ArrayList()
        randApple()
        addSnake()
    }

    private fun addSnake() {
        body.add(Point(width / 2 - 1, height / 2))
        for (i in 0..3) {
            body.add(Point(body[i].x + 1, body[i].y))
        }
    }


     fun move(currentDirection: Direction): Boolean {
        val firstPoint = body[0]
        var newPoint = Point()
        if (currentDirection == Direction.Left) {
            if (firstPoint.x <= 0) {
                newPoint = Point(width - 1, firstPoint.y)
            } else {
                newPoint = Point(firstPoint.x - 1, firstPoint.y)
            }

        } else if (currentDirection == Direction.Right) {
            if (firstPoint.x + 1 >= width) {
                newPoint = Point(0, firstPoint.y)
            } else {
                newPoint = Point(firstPoint.x + 1, firstPoint.y)
            }

        } else if (currentDirection == Direction.Down) {
            if (firstPoint.y + 1 >= height) {
                newPoint = Point(firstPoint.x, 0)
            } else {
                newPoint = Point(firstPoint.x, firstPoint.y + 1)
            }

        } else if (currentDirection == Direction.Up) {
            if (firstPoint.y <= 0) {
                newPoint = Point(firstPoint.x, height - 1)
            } else {
                newPoint = Point(firstPoint.x, firstPoint.y - 1)
            }
        }

        if (checkCollision(newPoint)) {
            currentState = States.Stop
            return false
        } else {
            body.removeAt(length - 1)
            body.add(0, newPoint)
            if (checkApple(firstPoint.x, firstPoint.y)) {
                length++
                body.add(1, apple)
                randApple()
                currentState = States.Apple
            } else if (premium != null && checkPremium(firstPoint.x, firstPoint.y)) {
                premiumPoints++
                randPremimum()
                currentState = States.Premium

            }
        }
        return true
    }


    private fun checkCollision(newPoint: Point): Boolean {
        val bodyCheck = body.contains(newPoint)
        val collision = map!!.checkCollision(newPoint.x, newPoint.y)
        return (collision || bodyCheck)
    }

    private fun checkApple(x: Int, y: Int): Boolean {
        return (apple.x === x && apple.y === y)
    }

    private fun checkPremium(x: Int, y: Int): Boolean {
        return (premium!!.x === x && premium!!.y === y)
    }


    fun randApple() {
        apple = map!!.randApple()
    }

    fun randPremimum() {
        premium = map!!.randApple()
    }

    fun getPremiumPoint(): Point? {
        randPremimum()
        Log.d("POINT" , premium!!.x.toString() + " " + premium!!.y.toString())
        return premium
    }
}