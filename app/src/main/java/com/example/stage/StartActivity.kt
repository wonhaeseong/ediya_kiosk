package com.example.stage

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.stage.startfragments.StartFragment


class StartActivity : AppCompatActivity() {
    lateinit var readableDb: SQLiteDatabase
    lateinit var writableDb: SQLiteDatabase
    lateinit var databaseControl: DatabaseControl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //db설정
        val databaseHelper = DatabaseHelper(this,"ediya.db",null,1)
        readableDb = databaseHelper.readableDatabase
        writableDb = databaseHelper.writableDatabase
        databaseControl = DatabaseControl()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = getColor(R.color.start_page_blue_color)
        setContentView(R.layout.activity_startpage)
        supportFragmentManager.beginTransaction().replace(R.id.start_fragment_container_view,
            StartFragment()
        ).commit()
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("message", "onRestart")
    }
    override fun onStart() {
        super.onStart()
        Log.d("message", "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("message", "onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("message", "onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("message", "onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("message", "onDestroy")
    }

}

