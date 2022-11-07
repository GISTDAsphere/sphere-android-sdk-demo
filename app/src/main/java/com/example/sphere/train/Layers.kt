package com.example.sphere.train

import android.content.Context
import android.view.View
import android.widget.Toast
import org.json.JSONArray
import th.or.gistda.sphere.SphereMap

class Layers(
    private val context: Context, private val sphere: SphereMap
) : Train(context) {

    var LANGUAGE = false
    var LAYER = false
    var LAYER_ADV = false

    fun createItem() = mutableListOf<View>().apply {
        if (LANGUAGE) {
            add(createButton("language") {
                sphere.call("Layers.language") {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
            add(createButton("th") {
                sphere.call("Layers.language", JSONArray().put("th"))
            })
            add(createButton("en") {
                sphere.call("Layers.language", JSONArray().put("en"))
            })
        }
        if (LAYER) {
            add(createButton("Simple Base") {
                sphere.call("Layers.setBase", SphereMap.SphereStatic("Layers", "SIMPLE"))
            })
            add(createButton("Hybrid Base") {
                sphere.call("Layers.setBase", SphereMap.SphereStatic("Layers", "HYBRID"))
            })
            add(createButton("Traffic Layer") {
                sphere.call("Layers.add", SphereMap.SphereStatic("Layers", "TRAFFIC"))
            })
            if (LAYER_ADV) {
                val wmts = SphereMap.SphereObject(
                    "Layer",
                    listOf(
                        "bluemarble_terrain",
                        mapOf(
                            "type" to SphereMap.SphereStatic("LayerType", "WMTS_REST"),
                            "url" to "https://ms.longdo.com/mapproxy/wmts",
                            "srs" to "GLOBAL_WEBMERCATOR",
                            "opacity" to 0.5,
                            "zoomRange" to mapOf("min" to 1, "max" to 9)
                        )
                    )
                )
                add(createButton("Add WMTS Layer") {
                    sphere.call("Layers.add", wmts)
                })
                add(createButton("Remove WMS Layer") {
                    sphere.call("Layers.remove", wmts)
                })
            }
            add(createButton("Clear") {
                sphere.call("Layers.clear")
            })
        }
    }

}