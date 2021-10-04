package com.rnmatsurisampleapp.modules

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpOnlyAudio
import com.rnmatsurisampleapp.event.StreamingErrorCode
import com.rnmatsurisampleapp.event.StreamingEvent
import com.rnmatsurisampleapp.utils.sendEvent

class StreamingModule(context: ReactApplicationContext) : ReactContextBaseJavaModule(context) {
    override fun getName(): String = "StreamingModule"

    private var rtmpClient: RtmpOnlyAudio? = null

    private val connectCheckerRtmp = object: ConnectCheckerRtmp {
        override fun onAuthErrorRtmp() {
            sendEvent(context, StreamingEvent.OnError(StreamingErrorCode.RTMP_CONNECTION_FAILED_AUTH))
        }

        override fun onAuthSuccessRtmp() {
            sendEvent(context, StreamingEvent.OnSuccessAuth)
        }

        override fun onConnectionFailedRtmp(reason: String) {
            sendEvent(context, StreamingEvent.OnError(StreamingErrorCode.RTMP_CONNECTION_FAILED_OR_CLOSED))
        }

        override fun onConnectionStartedRtmp(rtmpUrl: String) {
            sendEvent(context, StreamingEvent.OnStartedStream)
        }

        override fun onConnectionSuccessRtmp() {
            sendEvent(context, StreamingEvent.OnSuccessStream)
        }

        override fun onDisconnectRtmp() {
            TODO("Not yet implemented")
        }

        override fun onNewBitrateRtmp(bitrate: Long) {
            TODO("Not yet implemented")
        }
    }

    @ReactMethod
    fun startStreaming() {
        val rtmpUrl = "rtmp://exmaple.com:1935/rnmatsurisampleapp/abcdefg"
        rtmpClient = RtmpOnlyAudio(connectCheckerRtmp).also {
            it.setAuthorization("username", "password")
            it.prepareAudio(64 * 1024, 44100, false)
            it.startStream(rtmpUrl)
        }
    }

    @ReactMethod
    fun stopStreaming() {
        rtmpClient?.stopStream()
    }

}