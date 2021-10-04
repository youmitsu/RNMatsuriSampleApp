package com.rnmatsurisampleapp.utils

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.rnmatsurisampleapp.event.StreamingEvent
import com.rnmatsurisampleapp.event.toBodyMap

fun sendEvent(context: ReactApplicationContext, event: StreamingEvent) {
    val body = event.toBodyMap()
    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(event.emitName, body)
}
