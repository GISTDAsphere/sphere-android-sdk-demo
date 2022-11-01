package com.example.sphere.train

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import th.or.gistda.sphere.SphereMap

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        setupSphere()
    }

    private fun setupSphere() {
        // TODO findViewById<SphereMap>(R.id.sphere).load("", "")
    }
}