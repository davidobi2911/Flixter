package com.example.flixter


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManagerSupport = supportFragmentManager
        val fragmentTransactionObj = fragmentManagerSupport.beginTransaction()
        fragmentTransactionObj.replace(R.id.content, FlixsterFragment(), null).commit()
    }
}