package com.example.test2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pinyin4android.PinyinUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.ButterKnife;

public class Hanzi2PinyinActivity extends AppCompatActivity {

    @butterknife.Bind(R.id.et_input)
    EditText mEtInput;
    @butterknife.Bind(R.id.btn_convert)
    Button mBtnConvert;
    @butterknife.Bind(R.id.tv_result)
    TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hanzi2_pinyin);
        ButterKnife.bind(this);

        mBtnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hanzi = mEtInput.getText().toString().trim();
                String pinyin = PinyinUtil.toPinyin(Hanzi2PinyinActivity.this, hanzi);
                mTvResult.setText("转成的拼音是： " + pinyin);
            }
        });
    }

    private void netConnection() {
        String urll = "";
        try {
            URL url = new URL(urll);
            try {
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(3000);  //连接超时
                conn.setReadTimeout(3000);  //读取超时
                conn.setDoOutput(true);  //设置此方法，允许向服务器输出内容

                OutputStream os = conn.getOutputStream();
                os.write("ddddd".getBytes());
                os.flush();
                os.close();

                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    InputStream is = conn.getInputStream();
                    String s = getStringFromInputStream(is);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private String getStringFromInputStream(InputStream is) {
        byte[] bytes = new byte[1024];
        int len = -1;
        String s = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                bos.flush();
            }
            s = bos.toString();
            bos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }


}
