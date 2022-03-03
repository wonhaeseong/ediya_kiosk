package com.example.stage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.stage.startfragments.StartFragment


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = getColor(R.color.ediya_blue)
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

