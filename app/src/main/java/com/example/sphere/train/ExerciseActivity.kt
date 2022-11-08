package com.example.sphere.train

import android.os.Bundle
import android.webkit.JavascriptInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.google.gson.JsonParser
import org.json.JSONObject
import th.or.gistda.sphere.SphereMap

class ExerciseActivity : AppCompatActivity() {

    private lateinit var sphere: SphereMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        setupView()
    }

    private fun setupView() {
        setupSphere()
    }

    private fun setupSphere() {
        sphere = findViewById(R.id.sphere)
        sphere.load("test2022", packageName, this)
    }

    @JavascriptInterface
    fun ready(result: String) {
        changeBaseToHybrid()
        createNationalPark()
        createLandmarkArea()
    }

    private fun changeBaseToHybrid() {
        sphere.call("Layers.setBase", SphereMap.SphereStatic("Layers", "HYBRID"))
    }

    @JavascriptInterface
    fun location(result: String) {
        currentLocation {
            findViewById<AppCompatTextView>(R.id.location_text).text =
                String.format(getString(R.string.format_location), it["lon"], it["lat"])
        }
    }

    private fun currentLocation(result: (input: JSONObject) -> Unit) {
        sphere.call("location") {
            result.invoke(JSONObject(JsonParser.parseString(it).asString))
        }
    }

    private fun createNationalPark() {
        SphereMap.SphereObject(
            "Marker",
            listOf(
                JSONObject()
                    .put("lon", 101.517)
                    .put("lat", 14.321)
            ),
            1
        ).run {
            sphere.call("Overlays.add", this)
        }
    }

    private fun createLandmarkArea() {
        SphereMap.SphereObject(
            "Circle",
            listOf(
                JSONObject()
                    .put("lon", 101.517)
                    .put("lat", 14.321),
                0.1,
                JSONObject()
                    .put("lineColor", "rgba(0, 255, 0, 0.8")
                    .put("fillColor", "rgba(0, 255, 0, 0.4")
            ),
            2
        ).run {
            sphere.call("Overlays.add", this)
        }
    }

    @JavascriptInterface
    fun overlayClick(result: String) {
        sphere.call(
            "location",
            JSONObject()
                .put("lon", 101.517)
                .put("lat", 14.321)
        )
    }

}