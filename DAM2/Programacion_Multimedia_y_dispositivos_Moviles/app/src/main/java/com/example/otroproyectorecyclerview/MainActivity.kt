package com.example.otroproyectorecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.otroproyectorecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        val dataset = arrayOf("January", "February", "March", "April", "May", "June", "July" , "August", "September", "October", "November", "December")

        val adapter = Adapter(dataset)
        val rvMonths = binding.rvMonths

        rvMonths.layoutManager = LinearLayoutManager(this)
        rvMonths.adapter = adapter
    }
}