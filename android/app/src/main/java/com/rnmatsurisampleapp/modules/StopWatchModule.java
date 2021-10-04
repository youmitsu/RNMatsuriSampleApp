package com.rnmatsurisampleapp.modules;

import android.media.MediaRecorder;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class StopWatchModule extends ReactContextBaseJavaModule {

    private final StopWatch stopWatch = new StopWatch();

    public StopWatchModule(ReactApplicationContext context) {
        super(context);
    }

    @NonNull
    @Override
    public String getName() {
        return "StopWatchModule";
    }

    @ReactMethod
    public void start() {
        stopWatch.start();
    }

    @ReactMethod
    public void stop() {
        stopWatch.stop();
    }

    @ReactMethod
    public void reset() {
        stopWatch.reset();
    }

    private void sendEvent(ReactApplicationContext context) {
        WritableMap params = Arguments.createMap();
        params.putString("message", "Something is happened.");
        context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
               .emit("SomethingEvent", params);
    }

    private static class StopWatch {
        private long start = 0L;
        private float elapsedTime = 0f;
        private boolean paused = true;

        float getTimeSeconds() {
            float seconds;
            if (paused) {
                seconds = elapsedTime;
            } else {
                long now = System.currentTimeMillis();
                seconds = elapsedTime + (now - start) / 1000f;
            }
            return seconds;
        }

        void start() {
            start = System.currentTimeMillis();
            paused = false;
        }

        float stop() {
            if (!paused) {
                long now = System.currentTimeMillis();
                elapsedTime += (now - start) / 1000f;
                paused = true;
            }
            return elapsedTime;
        }

        void reset() {
            start = 0;
            elapsedTime = 0;
            paused = true;
        }
    }
}
