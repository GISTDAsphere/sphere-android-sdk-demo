package com.example.sphere.train

import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import com.google.gson.JsonParser
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
        sphere.load("test2022", packageName, this)
    }

    private fun setupList() {
        list = findViewById(R.id.list)
        Layers(this, sphere)
            .apply {
                LANGUAGE = false
                LAYER = false
                LAYER_ADV = false
            }
            .createItem()
            .forEach {
                list.addView(it)
            }
        Map(this, sphere)
            .apply {
                ZOOM = false
                LOCATION = false
                BOUND = false
                MOVE = false
                MODE = false
            }
            .createItem()
            .forEach {
                list.addView(it)
            }
        Overlays(this, sphere)
            .apply {
                MARKER = false
                POPUP = false
                GEOMETRY = false
                DASH = false
            }
            .createItem()
            .forEach {
                list.addView(it)
            }
    }

    @JavascriptInterface
    fun ready(result: String) {
        sphere.call("Layers.add", SphereMap.SphereStatic("Layers", "TRAFFIC"))
    }

    @JavascriptInterface
    fun location(result: String) {
        sphere.call("location") {
            Log.d("my-log", it)
        }
    }

    @JavascriptInterface
    fun overlayClick(result: String) {
        JsonParser.parseString(result).asJsonObject["data"].asString
            .let {
                JsonParser.parseString(
                    it
                ).asJsonObject["\$id"]
            }
            .asLong
            .let {
                SphereMap.SphereObject(" ", id = it)
            }
            .run {
                sphere.call("Overlays.remove", this)
            }
    }

    @JavascriptInterface
    fun error(result: String) {
        Log.e("my-log", result)
    }

}