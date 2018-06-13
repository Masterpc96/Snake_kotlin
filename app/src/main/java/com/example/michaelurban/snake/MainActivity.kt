//import android.content.Context
//import android.content.Intent
//import android.content.SharedPreferences
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Button
//import android.widget.ImageButton
//import android.widget.TextView
//import com.example.michaelurban.snake.R
//
//
//class MainActivity : AppCompatActivity(), View.OnClickListener {
//
//    private var square1: ImageButton? = null
//    private var square2: ImageButton? = null
//    private var tunnel_1: ImageButton? = null
//    private var tunnel: ImageButton? = null
//    private var lvl2: Button? = null
//    private var lvl3: Button? = null
//    private var lvl4: Button? = null
//    private var coins: TextView? = null
//    private val context = this
//    private var totalPremiumPoints = 0
//    private var preferences: SharedPreferences? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.main_view)
//
//        preferences = getSharedPreferences("com.example,michal.snake.PREFERENCES", Context.MODE_PRIVATE)
//
//        totalPremiumPoints = preferences!!.getInt("total_premium", 0)
//
//
//        checkingLevels() // check which levels are enabled and which are disabled
//
//
//        // set listeners
//        lvl2!!.setOnClickListener(this)
//        lvl3!!.setOnClickListener(this)
//        lvl4!!.setOnClickListener(this)
//
//        square1!!.setOnClickListener(this)
//        square2!!.setOnClickListener(this)
//        tunnel_1!!.setOnClickListener(this)
//        tunnel!!.setOnClickListener(this)
//
//        // set total coins text
//        _coins!!.text = "Your _coins " + totalPremiumPoints.toString()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        if (requestCode == 0 && resultCode == RESULT_OK) {
//            totalPremiumPoints += data.getIntExtra("TOTAL_COINS", 0)
//            _coins!!.text = "Your _coins " + totalPremiumPoints.toString()
//        }
//    }
//
//    override fun onClick(v: View) {
//        val intent = Intent(context, BoardActivity::class.java)
//        when (v.id) {
//            R.id.square1 -> intent.putExtra("MAP", Square())
//
//            R.id.square2 -> intent.putExtra("MAP", SquareLevel())
//
//            R.id.tunel -> intent.putExtra("MAP", Tunnel())
//
//            R.id.tunnel_1 -> intent.putExtra("MAP", Tunnel_1())
//            else -> unlockLevel(v as Button)
//        }
//        if (intent.hasExtra("MAP")) {
//            startActivityForResult(intent, 0)
//        }
//
//    }
//
//    override fun onStop() {
//        super.onStop()
//
//
//        val editor = preferences!!.edit()
//
//        editor.putInt("total_premium", totalPremiumPoints)
//        editor.apply()
//
//    }
//
//    private fun unlockLevel(level: Button) {
//        val editor = preferences!!.edit()
//        when (level.id) {
//            R.id.lvl2 -> if (totalPremiumPoints >= 5) {
//                square2!!.isEnabled = true
//                level.visibility = View.GONE
//                totalPremiumPoints -= 5
//                editor.putBoolean("LVL_2", true)
//            }
//            R.id.lvl3 -> if (totalPremiumPoints >= 10) {
//                tunnel_1!!.isEnabled = true
//                level.visibility = View.GONE
//                totalPremiumPoints -= 10
//                editor.putBoolean("LVL_3", true)
//            }
//            R.id.lvl4 -> if (totalPremiumPoints >= 15) {
//                tunnel!!.isEnabled = true
//                level.visibility = View.GONE
//                totalPremiumPoints -= 15
//                editor.putBoolean("LVL_4", true)
//            }
//        }
//        editor.apply()
//        _coins!!.text = "Your _coins " + totalPremiumPoints.toString()
//    }
//
//    private fun checkingLevels() {
//        if (preferences!!.getBoolean("LVL_2", false)) {
//            square2!!.isEnabled = true
//            lvl2!!.visibility = View.GONE
//        } else {
//            square2!!.isEnabled = false
//        }
//
//        if (preferences!!.getBoolean("LVL_3", false)) {
//            tunnel_1!!.isEnabled = true
//            lvl3!!.visibility = View.GONE
//        } else {
//            tunnel_1!!.isEnabled = false
//        }
//
//        if (preferences!!.getBoolean("LVL_4", false)) {
//            tunnel!!.isEnabled = true
//            lvl4!!.visibility = View.GONE
//        } else {
//            tunnel!!.isEnabled = false
//        }
//    }
//}