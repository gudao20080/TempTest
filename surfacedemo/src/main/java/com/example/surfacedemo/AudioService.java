package com.example.surfacedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.surfacedemo.util.L;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-01-15 14:04
 */
public class AudioService extends Service {
    private String TAG = getClass().getSimpleName();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        L.d("onBind");
        return new AudioBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.d("onStartCommand: ");


        return super.onStartCommand(intent, flags, startId);
    }

    class AudioBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }
}
