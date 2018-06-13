package com.example.michaelurban.snake

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.michaelurban.snake.Levels.Square
import com.example.michaelurban.snake.Levels.SquareLevel
import com.example.michaelurban.snake.Levels.Tunnel
import com.example.michaelurban.snake.Levels.Tunnel_1
import com.example.michaelurban.snake.OwnClass.User
import kotlinx.android.synthetic.main.hint.*
import kotlinx.android.synthetic.main.main_view.*
import kotlinx.android.synthetic.main.nick_name_dial.*

class Main2Activity : AppCompatActivity(), View.OnClickListener {


    private var _square1: ImageButton? = null
    private var _square2: ImageButton? = null
    private var _tunnel_1: ImageButton? = null
    private var _tunnel: ImageButton? = null
    private var _lvl2: Button? = null
    private var _trophy: ImageButton? = null
    private var _lvl3: Button? = null
    private var _lvl4: Button? = null
    private var _coins: TextView? = null
    private val context = this
    private var totalPremiumPoints = 0
    private var preferences: SharedPreferences? = null

    companion object {
        val user = User()
    }

    override fun onClick(v: View) {
        val intent = Intent(context, BoardActivity::class.java)
        when (v.id) {
            R.id.trophyButton -> startActivity(Intent(context, StatsActivity::class.java))

            R.id.square1 -> intent.putExtra("MAP", Square())

            R.id.square2 -> intent.putExtra("MAP", SquareLevel())

            R.id.tunel -> intent.putExtra("MAP", Tunnel())

            R.id.tunnel_1 -> intent.putExtra("MAP", Tunnel_1())
            else -> unlockLevel(v as Button)
        }
        if (intent.hasExtra("MAP")) {
            startActivityForResult(intent, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            totalPremiumPoints += data.getIntExtra("TOTAL COINS", 0)
            _coins!!.text = "Your coins " + totalPremiumPoints.toString()
        }
    }

    private fun unlockLevel(level: Button) {
        val editor = preferences!!.edit()
        when (level.id) {
            R.id.lvl2 -> if (totalPremiumPoints >= 5) {
                _square2!!.isEnabled = true
                level.visibility = View.GONE
                totalPremiumPoints -= 5
                editor.putBoolean("LVL_2", true)
            }
            R.id.lvl3 -> if (totalPremiumPoints >= 10) {
                _tunnel_1!!.isEnabled = true
                level.visibility = View.GONE
                totalPremiumPoints -= 10
                editor.putBoolean("LVL_3", true)
            }
            R.id.lvl4 -> if (totalPremiumPoints >= 15) {
                _tunnel!!.isEnabled = true
                level.visibility = View.GONE
                totalPremiumPoints -= 15
                editor.putBoolean("LVL_4", true)
            }
        }
        editor.apply()
        _coins!!.text = "Your _coins " + totalPremiumPoints.toString()
    }



    private fun checkingLevels() {
        if (preferences!!.getBoolean("LVL_2", false)) {
            _square2!!.isEnabled = true
            _lvl2!!.visibility = View.GONE
        } else {
            _square2!!.isEnabled = false
        }

        if (preferences!!.getBoolean("LVL_3", false)) {
            _tunnel_1!!.isEnabled = true
            _lvl3!!.visibility = View.GONE
        } else {
            _tunnel_1!!.isEnabled = false
        }

        if (preferences!!.getBoolean("LVL_4", false)) {
            _tunnel!!.isEnabled = true
            _lvl4!!.visibility = View.GONE
        } else {
            _tunnel!!.isEnabled = false
        }
    }

    private inner class NickDialog(context: Context) : Dialog(context, R.style.DialogTheme) {

        init {
            setContentView(R.layout.nick_name_dial)
            setCancelable(false)
            enterNick.setOnClickListener({
                user.nick = nickEditText.text.toString()
                Log.d("NAME", nickEditText.text.toString())
                dismiss()
            })

        }

    }

    override fun onStop() {
        super.onStop()
        val editor = preferences!!.edit()
        editor.putInt("total_premium", totalPremiumPoints)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_view)

        val dialog = NickDialog(context)
        dialog.show()

        preferences = getSharedPreferences("com.example,michal.snake.PREFERENCES", Context.MODE_PRIVATE)

        totalPremiumPoints = preferences!!.getInt("total_premium", 0)


        _square1 = square1
        _square2 = square2
        _tunnel_1 = tunnel_1
        _tunnel = tunel
        _lvl2 = lvl2
        _lvl3 = lvl3
        _lvl4 = lvl4
        _coins = coins

        _trophy = trophyButton

        _trophy!!.setOnClickListener(this)


        // set listeners
        _lvl2!!.setOnClickListener(this)
        _lvl3!!.setOnClickListener(this)
        _lvl4!!.setOnClickListener(this)

        _square1!!.setOnClickListener(this)
        _square2!!.setOnClickListener(this)
        _tunnel_1!!.setOnClickListener(this)
        _tunnel!!.setOnClickListener(this)

        checkingLevels() // check which levels are enabled and which are disabled

//         set total _coins text
        _coins!!.text = "Your coins " + totalPremiumPoints.toString()
    }
}
