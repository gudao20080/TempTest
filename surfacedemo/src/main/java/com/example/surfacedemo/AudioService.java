package com.example.surfacedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * User: WangKai(123940232@qq.com)
 * 2016-01-15 14:04
 */
public class AudioService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
