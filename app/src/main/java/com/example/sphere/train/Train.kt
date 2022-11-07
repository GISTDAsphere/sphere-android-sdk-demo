package com.example.sphere.train

import android.content.Context
import android.view.View
import com.google.android.material.button.MaterialButton

open class Train(private val context: Context) {

    fun createButton(text: String, callback: View.OnClickListener) =
        MaterialButton(context).apply {
            this.text = text
            setOnClickListener(callback)
        }
}