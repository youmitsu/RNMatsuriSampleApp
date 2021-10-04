package com.rnmatsurisampleapp.modules

import android.media.MediaRecorder
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import java.io.IOException

class RecorderModule(context: ReactApplicationContext?) : ReactContextBaseJavaModule(context) {
    override fun getName(): String = "RecorderModule"


    private var recorder: MediaRecorder? = null
    private var recordingFilePath: String? = null

    @ReactMethod
    fun startRecording(promise: Promise) {
        if (recorder != null || recordingFilePath != null) {
            promise.reject("INVALID_STATE", "recorder is must be null")
            return
        }

        val fileName = System.currentTimeMillis().toString()
        recordingFilePath = "${this.reactApplicationContext.filesDir.absolutePath}/audio_${fileName}.m4a"

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(recordingFilePath)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioSamplingRate(44100)

            try {
                prepare()
            } catch (e: IOException) {
                promise.reject(e)
            }

            start()
        }

        promise.resolve(null)
    }

    @ReactMethod
    fun stopRecording(promise: Promise) {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
        recordingFilePath = null
        promise.resolve(recordingFilePath)
    }
}