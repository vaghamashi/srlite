package com.example.externaldb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.externaldb.Adapter.MyAdapter
import com.example.externaldb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var db = MyDatabase(this)
        var list = db.getShayari()

        for (i in 0 until list.size){
            Log.e(TAG, "onCreate: ========"+ list[i].shayari)
        }

        binding.rcvShayari.layoutManager = LinearLayoutManager(this)
        binding.rcvShayari.adapter = MyAdapter(this,list)

    }
}

