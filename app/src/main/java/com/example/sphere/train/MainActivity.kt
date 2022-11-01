package com.example.sphere.train

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import th.or.gistda.sphere.SphereMap

class MainActivity : AppCompatActivity() {

    private lateinit var sphere: SphereMap
    private lateinit var list: LinearLayoutCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        setupSphere()
        setupList()
    }

    private fun setupSphere() {
        sphere = findViewById(R.id.sphere)
        initSphere()
    }

    private fun initSphere() {
        sphere.load("test2022", packageName)
    }

    private fun setupList() {
        list = findViewById(R.id.list)
        Layers(this, sphere).apply {
            LANGUAGE = true
            LAYER = true
            LAYER_ADV = true
        }.createItem().forEach {
            list.addView(it)
        }
    }
}