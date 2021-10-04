package com.rnmatsurisampleapp.event

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReadableMap

sealed class StreamingEvent(
        val name: String
) {
    val emitName: String
        get() = "StreamingModule:$name"

    object OnSuccessStream: StreamingEvent("onSuccessStream")
    object OnStartedStream: StreamingEvent("onStartedStream")
    object OnSuccessAuth: StreamingEvent("onSuccessAuthorization")
    class OnError(val code: StreamingErrorCode): StreamingEvent("onError")
}

enum class StreamingErrorCode(val message: String) {
    RTMP_CONNECTION_FAILED_AUTH("Failed to authorization for RTMP"),
    RTMP_CONNECTION_FAILED_OR_CLOSED("Failed or closed to RTMP connection"),
}

fun StreamingEvent.toBodyMap(): ReadableMap =
        Arguments.createMap().also {
            when (this)  {
                is StreamingEvent.OnError -> {
                    it.putInt("code", code.ordinal)
                    it.putString("message", code.message)
                }
                StreamingEvent.OnSuccessStream,
                StreamingEvent.OnStartedStream,
                StreamingEvent.OnSuccessAuth -> {
                    // Event has no body
                }
            }
        }