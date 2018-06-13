package com.example.michaelurban.snake

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.michaelurban.snake.Database.MyDatabase
import com.example.michaelurban.snake.OwnClass.User
import kotlinx.android.synthetic.main.stats.*



class StatsActivity : AppCompatActivity(){
    private lateinit var list : ListView
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.stats)


        val users = MyDatabase.getInstance(applicationContext).dao.users

        val usersL:ArrayList<String> = ArrayList()

        users.forEach { user: User ->  usersL.add(user.nick + " " + user.points)}

        // create list
        list = statsList


        adapter = ArrayAdapter(this, R.layout.row, usersL)

        list.setAdapter(adapter)


    }
}
