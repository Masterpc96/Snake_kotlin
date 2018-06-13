package com.example.michaelurban.snake.Levels
import android.graphics.Point
import com.example.michaelurban.snake.OwnClass.Line


import java.util.ArrayList
import java.util.Random

/**
 * Created by Michał on 21.09.2017.
 */

class Tunnel : Level() {

    override fun checkCollision(x: Int, y: Int): Boolean {
        val borders = x == 0 || y == 0 || x == width - 1 || y == height - 1
        val tunnel = x > 7 && x <= 27 && (y == 9 || y == 16)
        return (borders || tunnel)
    }

    override fun generateLines(spaceH: Int, spaceV: Int, pix: Int, screenWidth: Int, screenHeight: Int): ArrayList<Line> {
        //        x1 = 7;
        //        y1 = 8;
        //        x2 = 27;

        lines.clear()
        //up
        lines.add(Line(spaceH + 2, spaceV + pix / 2, screenWidth - spaceH - 3, spaceV + pix / 2))
        //bottom
        lines.add(Line(spaceH + 2, screenHeight - spaceV - pix / 2, screenWidth - spaceH - 3, screenHeight - spaceV - pix / 2))
        //left
        lines.add(Line(spaceH + pix / 2, spaceV + 2, spaceH + pix / 2, screenHeight - spaceV - 2))
        // right
        lines.add(Line(screenWidth - spaceH - pix / 2 - 1, spaceV + 2, screenWidth - spaceH - pix / 2 - 1, screenHeight - spaceV - 2))

        //tunnel
        lines.add(Line(spaceH + pix + 7 * pix, spaceV + pix / 2 + pix + 8 * pix, spaceH + pix + 27 * pix, spaceV + pix / 2 + pix + 8 * pix))
        lines.add(Line(spaceH + pix + 7 * pix, spaceV + pix / 2 + pix + (8 + 7) * pix, spaceH + pix + 27 * pix, spaceV + pix / 2 + pix + (8 + 7) * pix))
        return lines
    }

    override fun randApple(): Point {
        val random = Random()
        var x = random.nextInt(33) + 1
        var y = random.nextInt(23) + 1
        if ((x >= 7 || x <= 27) && (y == 8 || y == 15)) {
            x = random.nextInt(33) + 1
            y = random.nextInt(23) + 1
        }
        return Point(x, y)
    }

}
