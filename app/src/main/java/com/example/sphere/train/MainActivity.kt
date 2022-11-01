package com.example.sphere.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import th.or.gistda.sphere.SphereMap

class MainActivity : AppCompatActivity() {

    private lateinit var sphere: SphereMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        setupSphere()
    }

    private fun setupSphere() {
        sphere = findViewById(R.id.sphere)
        initSphere()
    }

    private fun initSphere() {
        sphere.load("test2022 ", packageName)
    }
}