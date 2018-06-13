//package com.example.michaelurban.snake.Views
//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.Point
//import android.util.AttributeSet
//import android.view.MotionEvent
//import android.view.View
//import com.example.michaelurban.snake.Enums.Direction
//import com.example.michaelurban.snake.Levels.Level
//import com.example.michaelurban.snake.OwnClass.Line
//import java.util.ArrayList
//
//package com.example.michaelurban.snake.Views
//
//import android.content.Context
//import android.graphics.Canvas
//import android.graphics.Color
//import android.graphics.Paint
//import android.graphics.Point
//import android.view.MotionEvent
//import android.view.View
//import com.example.michaelurban.snake.Enums.Direction
//import com.example.michaelurban.snake.Levels.Level
//import com.example.michaelurban.snake.OwnClass.Line
//import java.util.ArrayList
//import android.util.AttributeSet
//
//open class Ground constructor(context: Context, attr: AttributeSet) : View(context, attr) {
//    var body: ArrayList<Point>? = null // snake points collection
//
//    var moved = true
//
//
//    var currentDirection: Direction = Direction.Left // current direction in which snake moves
//
//    var lines: ArrayList<Line>? = null // map lines
//
//    var _width: Int = 0 // this view _width
//    var _height: Int = 0 // this view _height
//
//    var paint: Paint? = null // paint for line
//
//    var gameWidth: Int = 0 // game _width from another class
//    var gameHeight: Int = 0 // game _height from another class
//
//    var spaceH: Int = 0 // horizontal space
//    var spaceV: Int = 0 // vertical space
//
//    var pix: Int = 0 // single pix size
//
//    var apple: Point? = null // apple point
//
//    var map: Level? = null // Map
//
//    var premium: Point? = null // Premium point
//
//
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        _width = w
//        _height = h
//
//        pixSize()
//    }
//
//
//    fun pixSize() {
//        pix = Math.min(_height, _width) / 26
//        spaceH = (_width - pix * gameWidth) / 2
//
//        spaceV = (_height - pix * gameHeight) / 2
//
//        paint = Paint()
//    }
//
//
//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
//
//        drawMap(canvas)
//        for (p in body!!) {
//            canvas.drawRect((p.x * pix + spaceH).toFloat(), (p.y * pix + spaceV).toFloat(), (p.x * pix + spaceH + pix - 2).toFloat(), (p.y * pix + spaceV + pix - 2).toFloat(), paint!!)
//        }
//
//        canvas.drawRect((apple!!.x * pix + spaceH).toFloat(), (apple!!.y * pix + spaceV).toFloat(), (apple!!.x * pix + spaceH + pix - 2).toFloat(), (apple!!.y * pix + spaceV + pix - 2).toFloat(), paint!!)
//        if (premium != null) {
//            canvas.drawCircle((premium!!.x * pix + spaceH + pix / 2).toFloat(), (premium!!.y * pix + spaceV + pix / 2).toFloat(), 13f, paint!!)
//        }
//    }
//
//    fun drawMap(canvas: Canvas) {
//        paint!!.strokeWidth = (pix - 2).toFloat()
//        paint!!.color = Color.BLACK
//
//        lines = map!!.generateLines(spaceH, spaceV, pix, _width, _height)
//
//        for (line in lines!!) {
//            canvas.drawLine(line.startX.toFloat(), line.startY.toFloat(), line.stopX.toFloat(), line.stopY.toFloat(), paint!!)
//        }
//    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val x = event.x
//        val y = event.y
//        if (moved) {
//            if (x >= 0 && x <= _width * 0.25 && currentDirection !== Direction.Right) {
//                currentDirection = Direction.Left
//            } else if (x <= _width && x >= _width * 0.75 && currentDirection !== Direction.Left) {
//                currentDirection = Direction.Right
//            } else if (x < _width * 0.75 && x > _width * 0.25 && y >= _height / 2 && y <= _height && currentDirection !== Direction.Up) {
//                currentDirection = Direction.Down
//            } else if (x < _width * 0.75 && x > _width * 0.25 && y < _height / 2 && y >= 0 && currentDirection !== Direction.Down) {
//                currentDirection = Direction.Up
//            }
//            moved = false
//        }
//
//        return super.onTouchEvent(event)
//    }
//
//
//}
