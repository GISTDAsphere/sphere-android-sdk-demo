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
            add(createItem("language") {
                sphere.call("Layers.language") {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            })
            add(createItem("th") {
                sphere.call("Layers.language", JSONArray().put("th"))
            })
            add(createItem("en") {
                sphere.call("Layers.language", JSONArray().put("en"))
            })
        }
        if (LAYER) {
            add(createItem("Simple Base") {
                sphere.call("Layers.setBase", SphereMap.SphereStatic("Layers", "SIMPLE"))
            })
            add(createItem("Hybrid Base") {
                sphere.call("Layers.setBase", SphereMap.SphereStatic("Layers", "HYBRID"))
            })
            add(createItem("Traffic Layer") {
                sphere.call("Layers.add", SphereMap.SphereStatic("Layers", "TRAFFIC"))
            })
            if (LAYER_ADV) {
                val wms = SphereMap.SphereObject(
                    "Layer",
                    listOf(
                        "roadnet2:Road_FGDS",
                        mapOf(
                            "type" to SphereMap.SphereStatic("LayerType", "WMS"),
                            // TODO: CHANGE URL
                            "url" to "https://msq.thaimap.longdo.org/vector/test-tile.php",
                            "zoomRange" to mapOf("min" to 1, "max" to 9),
                            "refresh" to 180,
                            "opacity" to 0.5,
                            "zIndex" to 10,
                            "bound" to mapOf(
                                "minLon" to 100,
                                "minLat" to 10,
                                "maxLon" to 105,
                                "maxLat" to 20
                            )
                        )
                    )
                )
                add(createItem("Add WMS Layer") {
                    sphere.call("Layers.add", wms)
                })
                add(createItem("Remove WMS Layer") {
                    sphere.call("Layers.remove", wms)
                })
            }
            add(createItem("Clear") {
                sphere.call("Layers.clear")
            })
        }
    }

}