package com.example.sphere.train

import android.content.Context
import android.view.View
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import th.or.gistda.sphere.SphereMap

class Overlays(
    context: Context, private val sphere: SphereMap
) : Train(context) {

    var MARKER = false
    var POPUP = false
    var GEOMETRY = false
    var DASH = false

    fun createItem() = mutableListOf<View>().apply {
        if (MARKER) {
            createButton("marker") {
                SphereMap.SphereObject("Marker", mutableListOf(
                    JSONObject().put("lon", (90..100).random()).put("lat", (10..20).random()),
                ).apply {
                    if (POPUP) {
                        JSONObject().put("title", "Marker").put("detail", "Drag me").run {
                            add(this)
                        }
                    }
                }).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
        }
        if (GEOMETRY) {
            createButton("line") {
                SphereMap.SphereObject("Polyline", mutableListOf<Any>(
                    JSONArray().put(
                        JSONObject().put("lon", (90..100).random()).put("lat", (10..20).random()),
                    ).put(
                        JSONObject().put("lon", (90..100).random()).put("lat", (10..20).random()),
                    )
                ).apply {
                    if (DASH) {
                        JSONObject().put(
                            "lineStyle", SphereMap.SphereStatic("LineStyle", "Dashed")
                        ).put("pointer", true).run {
                            add(this)
                        }
                    }
                }).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
            createButton("polygon") {
                SphereMap.SphereObject(
                    "Polygon",
                    listOf(
                        JSONArray().apply {
                            repeat(4) {
                                put(
                                    JSONObject()
                                        .put("lon", (90..100).random())
                                        .put("lat", (10..20).random())
                                )
                            }
                        }
                    )
                ).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
            createButton("circle") {
                SphereMap.SphereObject(
                    "Circle",
                    listOf(
                        JSONObject()
                            .put("lon", (90..100).random())
                            .put("lat", (10..20).random()),
                        1,
                        JSONObject()
                            .put("lineWidth", 2)
                            .put(
                                "lineColor",
                                "rgba(255, 0, 0, 0.8)"
                            )
                            .put(
                                "fillColor",
                                "rgba(255, 0, 0, 0.4)"
                            )
                    )
                ).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
            createButton("dot") {
                SphereMap.SphereObject(
                    "Dot",
                    listOf(
                        JSONObject()
                            .put("lon", (90..100).random())
                            .put("lat", (10..20).random()),
                        JSONObject()
                            .put("lineWidth", 20)
                    )
                ).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
            createButton("donut") {
                SphereMap.SphereObject(
                    "Polygon",
                    listOf(
                        JSONArray().apply {
                            put(
                                JSONObject()
                                    .put("lon", 101)
                                    .put("lat", 15)
                            )
                            put(
                                JSONObject()
                                    .put("lon", 105)
                                    .put("lat", 15)
                            )
                            put(
                                JSONObject()
                                    .put("lon", 103)
                                    .put("lat", 12)
                            )
                            put(null)
                            put(
                                JSONObject()
                                    .put("lon", 103)
                                    .put("lat", 14.9)
                            )
                            put(
                                JSONObject()
                                    .put("lon", 102.1)
                                    .put("lat", 13.5)
                            )
                            put(
                                JSONObject()
                                    .put("lon", 103.9)
                                    .put("lat", 13.5)
                            )
                        }
                    )
                ).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
            createButton("rectangle") {
                SphereMap.SphereObject(
                    "Rectangle",
                    listOf(
                        JSONObject()
                            .put("lon", (90..100).random())
                            .put("lat", (10..20).random()),
                        JSONObject()
                            .put("width", (1..3).random())
                            .put("height", (1..3).random())
                    )
                ).run {
                    sphere.call("Overlays.add", this)
                }
            }.run {
                add(this)
            }
        }
        createButton("clear") {
            sphere.call("Overlays.clear")
        }.run {
            add(this)
        }
    }
}