package com.example.michaelurban.snake

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.michaelurban.snake.Database.MyDatabase
import com.example.michaelurban.snake.Enums.Direction
import com.example.michaelurban.snake.Enums.States
import com.example.michaelurban.snake.GameEngine.GameEngine
import com.example.michaelurban.snake.Levels.Level
import com.example.michaelurban.snake.Views.Ground
import kotlinx.android.synthetic.main.board_view.*
import kotlinx.android.synthetic.main.hint.*
import kotlinx.android.synthetic.main.pause_menu.*

class BoardActivity : AppCompatActivity() {

    val dimension = DisplayMetrics()
    val handler = android.os.Handler()
    var game: GameEngine? = null
    var _score: TextView? = null
    var _coins: TextView? = null
    var _pause: ImageButton? = null
    var snake: Ground? = null
    val DELAY = 300
    val INTERVAL = 50
    val BREAK = 40
    var currentInterval = 0
    var totalPremium = 0
    var map: Level? = null
    var gameRunner: RunTheGame? = null
    val context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board_view)

        val pref = getSharedPreferences("com.example,michal.snake.PREFERENCES", Context.MODE_PRIVATE)


         _score = score
         _coins = coins
         _pause = pause
         snake = SnakeGround


        windowManager.defaultDisplay.getMetrics(dimension)

        map = intent.extras.getSerializable("MAP") as Level?


        gameRunner = RunTheGame()
        game = GameEngine(map)

        snake?._width = game!!.width
        snake?._height = game!!.height




        _pause!!.setOnClickListener(View.OnClickListener {
            val dialog = PauseDialog(context)
            handler.removeCallbacks(gameRunner)
            dialog.show()
        })


        if (true) {
            val dialog = TipDialog(context)
            dialog.getWindow()!!.setBackgroundDrawableResource(R.color.semi_green)
            dialog.show()
            startTheGame(false)
            dialog.setOnDismissListener(DialogInterface.OnDismissListener { handler.postDelayed(gameRunner, DELAY.toLong()) })

            val editor = pref.edit()
            editor.putBoolean("first_use", false)
            editor.apply()

        } else {
            startTheGame(true)
        }

    }

    private fun startTheGame(run: Boolean) {
        game = GameEngine(map)

        snake?.setGameDimension(game!!.width, game!!.height)

        snake?.currentDirection = Direction.Left

        snake?.map = map

        snake?.body = game?.body


        snake?.premium = null

        snake?.apple = game?.apple

        if (run) {
            handler.postDelayed(gameRunner, DELAY.toLong())
        }

        snake?.invalidate()
    }

    override fun onBackPressed() {
        val dialog = PauseDialog(context)
        handler.removeCallbacks(gameRunner)
        dialog.show()
    }

    override fun onPause() {
        super.onPause()
        onBackPressed()
    }

    // private classes

    inner class PauseDialog(context: Context) : Dialog(context, R.style.DialogTheme), View.OnClickListener {
        private val _restart: ImageButton
        private val _home: ImageButton
        private val _play: ImageButton

        init {
            setContentView(R.layout.pause_menu)
            setCancelable(false)
            _restart = restart
            _play = play
            _home = home

            _restart.setOnClickListener(this)
            _play.setOnClickListener(this)
            _home.setOnClickListener(this)
        }


        override fun onClick(v: View) {
            when (v.id) {
                R.id.restart -> {
                    startTheGame(true)
                    _score?.setText("score 0")
                    _coins?.setText("coins 0")
                    totalPremium += game!!.premiumPoints
                }
                R.id.home -> {
                    val returnIntent = Intent()
                    totalPremium += game!!.premiumPoints
                    returnIntent.putExtra("TOTAL COINS", totalPremium)
                    setResult(Activity.RESULT_OK, returnIntent)
                    Main2Activity.user.points = game!!.length - 5
                    val a = MyDatabase.getInstance(context).dao.nickCount(Main2Activity.user.nick)
                    if(a==0){
                        MyDatabase.getInstance(context).dao.insertProduct(Main2Activity.user)
                    }else{
                        MyDatabase.getInstance(context).dao.updateUser(Main2Activity.user)
                    }
                    this@BoardActivity.finish()
                }
                R.id.play -> {
                    snake?.invalidate()
                    handler.postDelayed(gameRunner, DELAY.toLong())
                }
            }
            this.dismiss()
        }
    }

    private inner class TipDialog(context: Context) : Dialog(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen) {

        init {
            setContentView(R.layout.hint)

            closeTip.setOnClickListener { dismiss() }
        }

    }

    inner class RunTheGame : Runnable {

        override fun run() {
            if (currentInterval == INTERVAL) {
                snake?.premium = game!!.getPremiumPoint()
                currentInterval = 0
                Log.d("INTERVAL", "GENERATE ")
            }

            if (currentInterval == BREAK) {
                snake?.premium = null
                game?.premium = null
                Log.d("INTERVAL", "REMOVE ")
            }

            snake?.moved = game!!.move(snake!!.currentDirection)
            snake!!.body = game!!.body

            if (game!!.currentState === States.Apple) {
                snake?.apple = game!!.apple
                _score!!.setText(getString(R.string.score) + " " + (game!!.length - 5))
            } else if (game!!.currentState === States.Premium) {
                snake?.premium = null
                _coins!!.setText("coins " + " " + game!!.premiumPoints)
            }
            snake?.invalidate()

            if (game!!.currentState === States.Running || game!!.currentState === States.Apple || game!!.currentState === States.Premium) {
                currentInterval++
                handler.postDelayed(this, DELAY.toLong())
            } else if (game!!.currentState === States.Stop) {
                handler.removeCallbacks(this)
            }
        }
    }
}
