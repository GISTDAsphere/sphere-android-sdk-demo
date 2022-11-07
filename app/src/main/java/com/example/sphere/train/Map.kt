package com.example.sphere.train

import android.content.Context
import android.view.View
import android.widget.Toast
import org.json.JSONObject
import th.or.gistda.sphere.SphereMap

class Map(private val context: Context, private val sphere: SphereMap) : Train(context) {

    var ZOOM = false
    var LOCATION = false
    var BOUND = false
    var MOVE = false
    var MODE = false

    fun createItem() = mutableListOf<View>().apply {
        if (ZOOM) {
            add(createItem("zoom") {
                val zoom = (1..20).random()
                sphere.call("zoom", zoom)
            })
            add(createItem("zoom (get)") {
                sphere.call("zoom") {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
        }
        if (LOCATION) {
            add(createItem("location") {
                sphere.call(
                    "location", JSONObject()
                        .put("lon", (90..100).random())
                        .put("lat", (10..20).random())
                )
            })
            add(createItem("location (get)") {
                sphere.call("location") {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
        }
        if (BOUND) {
            add(createItem("bound") {
                sphere.call(
                    "bound",
                    JSONObject()
                        .put("minLon", 97.3758964376)
                        .put("minLat", 5.69138418215)
                        .put("maxLon", 105.589038527)
                        .put("maxLat", 20.4178496363)
                )
            })
        }
        if (MOVE) {
            add(createItem("left") {
                sphere.call(
                    "move",
                    JSONObject()
                        .put("x", -100)
                        .put("y", 0)
                )
            })
            add(createItem("right") {
                sphere.call(
                    "move",
                    JSONObject()
                        .put("x", 100)
                        .put("y", 0)
                )
            })
            add(createItem("up") {
                sphere.call(
                    "move",
                    JSONObject()
                        .put("x", 0)
                        .put("y", -100)
                )
            })
            add(createItem("down") {
                sphere.call(
                    "move",
                    JSONObject()
                        .put("x", 0)
                        .put("y", 100)
                )
            })
        }
        if (MODE) {
            add(createItem("Dark") {
                sphere.call(
                    "enableFilter",
                    SphereMap.SphereStatic("Filter", "Dark")
                )
            })
            add(createItem("Light") {
                sphere.call(
                    "enableFilter",
                    SphereMap.SphereStatic("Filter", "Light")
                )
            })
            add(createItem("None") {
                sphere.call(
                    "enableFilter",
                    SphereMap.SphereStatic("Filter", "None")
                )
            })
        }
    }

}