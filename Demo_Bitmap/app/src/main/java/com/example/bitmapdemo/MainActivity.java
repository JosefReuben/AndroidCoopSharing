package com.example.bitmapdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.view.View.OnClickListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageOne);
        button = findViewById(R.id.btnChangeImage);

        addListenerOnButton();
    }

    public class MyAsync extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {

            try {
                URL url = new URL("http://www.gstatic.com/webp/gallery/1.jpg");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }
    }


    public void addListenerOnButton() {

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                MyAsync obj = new MyAsync(){

                    @Override
                    protected void onPostExecute(Bitmap bmp) {
                        super.onPostExecute(bmp);

                        Bitmap bm = bmp;

                        image.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        image.setImageBitmap(Bitmap.createScaledBitmap(bm, 1500, 1500, false));

                    }
                };

                obj.execute();
            }

        });

    }
}
