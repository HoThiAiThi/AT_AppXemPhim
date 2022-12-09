package com.example.bt_cuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ChiTietActivity extends AppCompatActivity {
    TextView ten, namChieu, tomTat;
    ImageView nen,anh, backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        ten = (TextView) findViewById(R.id.ten_ct);
        namChieu = (TextView) findViewById(R.id.namChieu);
        tomTat = (TextView) findViewById(R.id.thongtin_ct);
        nen = (ImageView) findViewById(R.id.iv_nen);
        anh = (ImageView) findViewById(R.id.iv_anh_ct);
        backbtn = (ImageView) findViewById(R.id.back_btn);

        Intent intent = getIntent();
        ten.setText(intent.getStringExtra("tenPhim"));
        namChieu.setText(intent.getStringExtra("namChieu"));
        tomTat.setText(intent.getStringExtra("tomTat"));
        Glide.with(this).load(intent.getStringExtra("anh")).into(anh);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietActivity.this, Phim.class));
            }
        });
    }

//    private class LoadImage extends AsyncTask<String, Void, Bitmap> {
//        Bitmap bitmapHinh = null;
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//
//                InputStream inputStream = url.openConnection().getInputStream();
//
//                bitmapHinh = BitmapFactory.decodeStream(inputStream);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return bitmapHinh;
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            super.onPostExecute(bitmap);
//            nen.setImageBitmap(bitmap);
//            anh.setImageBitmap(bitmap);
//        }
//    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChiTietActivity.this, Phim.class));
    }
}