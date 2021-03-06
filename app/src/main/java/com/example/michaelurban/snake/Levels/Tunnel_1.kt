package com.example.michaelurban.snake.Levels
import android.graphics.Point
import android.util.Log
import com.example.michaelurban.snake.OwnClass.Line

import java.util.ArrayList
import java.util.Random

/**
 * Created by Michał on 21.09.2017.
 */

class Tunnel_1 : Level() {

    override fun checkCollision(x: Int, y: Int): Boolean {
        Log.d("POSITION", x.toString() + " " + y.toString())

        val verticalBorders = x == 0 || x == width - 1
        val horizontalBorders = (x <= 15 || x >= 20) && (y == 0 || y == height - 1)
        val leftLine = x == 12 && y >= 2 && y <= 23
        val rightLine = x == 23 && y >= 2 && y <= 23

        return (verticalBorders || horizontalBorders || leftLine || rightLine)
    }

    override fun generateLines(spaceH: Int, spaceV: Int, pix: Int, screenWidth: Int, screenHeight: Int): ArrayList<Line> {
        //        x1 = 12;
        //        x2 = 23;
        //        y1 = 1;
        //        y2 = 23;

        lines.clear()

        // up
        lines.add(Line(spaceH + 2, spaceV + pix / 2, spaceH + 16 * pix, spaceV + pix / 2))
        lines.add(Line(spaceH + (16 + 4) * pix, spaceV + pix / 2, screenWidth - spaceH - 3, spaceV + pix / 2))

        //bottom
        lines.add(Line(spaceH + 2, screenHeight - spaceV - pix / 2, spaceH + 16 * pix, screenHeight - spaceV - pix / 2))
        lines.add(Line(spaceH + (16 + 4) * pix, screenHeight - spaceV - pix / 2, screenWidth - spaceH - 3, screenHeight - spaceV - pix / 2))

        //left
        lines.add(Line(spaceH + pix / 2, spaceV + 2, spaceH + pix / 2, screenHeight - spaceV - 2))

        // right
        lines.add(Line(screenWidth - spaceH - pix / 2 - 1, spaceV + 2, screenWidth - spaceH - pix / 2 - 1, screenHeight - spaceV - 2))


        //horizontal
        lines.add(Line(spaceH + pix / 2 + 12 * pix, spaceV + pix + pix, spaceH + pix / 2 + 12 * pix, spaceV + pix + 23 * pix - 2))
        lines.add(Line(spaceH + pix / 2 + 23 * pix, spaceV + pix + pix, spaceH + pix / 2 + 23 * pix, spaceV + pix + 23 * pix - 2))


        return lines
    }

    override fun randApple(): Point {
        val random = Random()
        var x = random.nextInt(33) + 1
        var y = random.nextInt(23) + 1

        val leftLine = x == 12 && y >= 2 && y <= 23
        val rightLine = x == 23 && y >= 2 && y <= 23

        if (leftLine || rightLine) {
            x = random.nextInt(33) + 1
            y = random.nextInt(23) + 1
        }
        return Point(x, y)
    }
}
