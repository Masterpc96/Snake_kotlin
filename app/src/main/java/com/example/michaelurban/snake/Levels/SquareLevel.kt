package com.example.michaelurban.snake.Levels
import android.graphics.Point
import com.example.michaelurban.snake.OwnClass.Line
import java.util.*

class SquareLevel : Level() {

    override fun checkCollision(x: Int, y: Int): Boolean {
        val verticalBorders = (x == 0 || x == width - 1) && (y <= 10 || y >= 15)
        val horizontalBorders = (x <= 15 || x >= 20) && (y == 0 || y == height - 1)

        return (verticalBorders || horizontalBorders)
    }

    override fun generateLines(spaceH: Int, spaceV: Int, pix: Int, screenWidth: Int, screenHeight: Int): ArrayList<Line> {
        lines.clear()

        // up
        lines.add(Line(spaceH + 2, spaceV + pix / 2, spaceH + 16 * pix, spaceV + pix / 2))
        lines.add(Line(spaceH + (16 + 4) * pix, spaceV + pix / 2, screenWidth - spaceH - 3, spaceV + pix / 2))

        //bottom
        lines.add(Line(spaceH + 2, screenHeight - spaceV - pix / 2, spaceH + 16 * pix, screenHeight - spaceV - pix / 2))
        lines.add(Line(spaceH + (16 + 4) * pix, screenHeight - spaceV - pix / 2, screenWidth - spaceH - 3, screenHeight - spaceV - pix / 2))

        // left
        lines.add(Line(spaceH + pix / 2, spaceV + 2, spaceH + pix / 2, spaceV + 11 * pix))
        lines.add(Line(spaceH + pix / 2, spaceV + 15 * pix, spaceH + pix / 2, screenHeight - spaceV - 2))


        // right
        lines.add(Line(screenWidth - spaceH - pix / 2 - 1, spaceV + 2, screenWidth - spaceH - pix / 2 - 1, spaceV + 11 * pix))
        lines.add(Line(screenWidth - spaceH - pix / 2 - 1, spaceV + 15 * pix, screenWidth - spaceH - pix / 2 - 1, screenHeight - spaceV - 2))
        return lines
    }

    override fun randApple(): Point {
        val random = Random()
        val x = random.nextInt(33) + 1
        val y = random.nextInt(23) + 1
        return Point(x, y)
    }

    override fun randPremium(): Point {
        return randApple()
    }
}
