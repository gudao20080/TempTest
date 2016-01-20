package com.example.surfacedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.surfacedemo.util.L;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MediaActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    @Bind(R.id.btn_start_service)
    Button mBtnStartService;
    @Bind(R.id.btn_stop_service)
    Button mBtnStopService;
    @Bind(R.id.btn_have_fun)
    Button mBtnHaveFun;
    @Bind(R.id.btn_bind_service)
    Button mBtnBindService;
    @Bind(R.id.btn_unBind_service)
    Button mBtnUnBindService;
    private Intent mIntent;
    private AudioServiceConnection mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        ButterKnife.bind(this);

        mIntent = new Intent(MediaActivity.this, AudioService.class);
        mBtnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MediaActivity.this, AudioService.class));
            }
        });

        mBtnBindService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mConnection = new AudioServiceConnection();
                bindService(mIntent, mConnection, BIND_AUTO_CREATE);
            }
        });

        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(this, Uri.parse("ddd"));
            player.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class AudioServiceConnection implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.d( "onServiceConnected");
            if (null != service && service instanceof AudioService.AudioBinder) {
                AudioService audioService = ((AudioService.AudioBinder) service).getService();
                L.d(audioService.toString());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.d( "onServiceDisconnected");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
        stopService(mIntent);
    }
}
