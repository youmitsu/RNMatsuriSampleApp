package com.rnmatsurisampleapp.modules

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import android.widget.Toast

class ToastModule(context: ReactApplicationContext?) : ReactContextBaseJavaModule(context) {
    override fun getName(): String = "ToastModule"

    @ReactMethod
    fun showToast(text: String?) {
        val toast = Toast.makeText(reactApplicationContext, text, Toast.LENGTH_LONG)
        toast.show()
    }
}
