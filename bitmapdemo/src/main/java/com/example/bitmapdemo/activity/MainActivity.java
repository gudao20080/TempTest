package com.example.bitmapdemo.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bitmapdemo.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private final int outWidth = 100;
    private final int outHeight = 100;
    private final int inSample = 2;



    @Bind(R.id.iv)
    ImageView mIv;
    @Bind(R.id.btn_decode_from_file)
    Button mBtnDecodeFromFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                copyFile2CacheDir();
            }
        });



        mBtnDecodeFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.outWidth = outWidth;
                options.outHeight = outHeight;
                File copiedFile = new File(getCacheDir(), "splashing.png");
                if (copiedFile.exists()) {
                    Bitmap bitmap = BitmapFactory.decodeFile(copiedFile.getPath(), options);
                    Log.d(TAG, "decodeFile: " + bitmap.getByteCount() / (1024f * 1024) + " M");

                }
            }
        });

        decodeResource();       //从drawable中获取图片
        decodeRawResource();       //从res/raw中获取图片
        decodeStreamFromRaw();      //从res/raw中通过流的方式获取图片
        decodeStreamFromAssets();   //从assets 中通过流的方式获取图片

//        mIv.setImageBitmap(bitmap);
    }

    private void decodeResource() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = outWidth;
        options.outHeight = outHeight;
        options.inSampleSize = inSample;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.splashing, options);
        Log.d(TAG, "decodeResource: " + bitmap.getByteCount() / (1024f * 1024) + " M");
    }

    private void decodeRawResource() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = outWidth;
        options.outHeight = outHeight;
        options.inSampleSize = inSample;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.raw.splashing, options);
        Log.d(TAG, "decodeResource from raw: " + bitmap1.getByteCount() / (1024f * 1024) + " M");
    }

    private void decodeStreamFromRaw() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = outWidth;
        options.outHeight = outHeight;
        options.inSampleSize = inSample;
        InputStream is = getResources().openRawResource(R.raw.splashing);
//        Bitmap bitmap2 = BitmapFactory.decodeStream(is);
        Bitmap bitmap2 = BitmapFactory.decodeStream(is, null, options);
        Log.d(TAG, "decodeStream from raw: " + bitmap2.getByteCount() / (1024f * 1024) + " M");
    }

    private void decodeStreamFromAssets() {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.outWidth = outWidth;
        options.outHeight = outHeight;
//        options.inSampleSize = inSample;
        options.inScaled = true;
        options.inDensity = 400;
        options.inTargetDensity = 200;

        try {
            InputStream is1 = getAssets().open("splashing.png");
            Bitmap bitmap3 = BitmapFactory.decodeStream(is1, null, options);
            Log.d(TAG, "decodeStream from assets: " + bitmap3.getByteCount() / (1024f * 1024) + " M");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void copyFile2CacheDir() {
        File cacheDir = getCacheDir();
        InputStream is = getResources().openRawResource(R.raw.splashing);
        File copiedFile = new File(cacheDir, "splashing.png");
        try {
            FileOutputStream fos = new FileOutputStream(copiedFile);
            byte[] bytes = new byte[1024];
            try {
                int len;
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    fos.flush();
                }
                is.close();
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取采样比例
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public  int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int
        reqHeight) {
        // Raw height and width of image
        final int width = options.outWidth;
        final int height = options.outHeight;
        Log.d(TAG, "calculateInSampleSize--> options.outWidth: " + options.outWidth + " options" +
            ".outHeight: " + options.outHeight);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            int halfHeight = height / 2;
            int halfWidth = width / 2;

            //选择宽或高的最小比值
            while ((halfHeight / inSampleSize) > reqHeight
                || (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
}
