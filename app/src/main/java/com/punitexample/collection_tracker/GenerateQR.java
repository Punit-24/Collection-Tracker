package com.punitexample.collection_tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQR extends AppCompatActivity {

    ImageView qrCode;
    String input;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    Button  saveImg;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        qrCode = (ImageView) findViewById(R.id.qrCode);
        saveImg = (Button) findViewById(R.id.saveImg);

        input = Add.sNum.getText().toString().trim();
        if(input.length() > 0) {
            WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int small = width < height ? width : height;
            small = small * 3 / 4;
            qrgEncoder = new QRGEncoder(input, null, QRGContents.Type.TEXT, small);
            try {
                bitmap = qrgEncoder.encodeAsBitmap();
                qrCode.setImageBitmap(bitmap);
                saveImg.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
         Toast.makeText(GenerateQR.this,"Add serial number",Toast.LENGTH_SHORT).show();
        }



        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) qrCode.getDrawable();
                bitmap = bitmapDrawable.getBitmap();
                File path = Environment.getExternalStorageDirectory();
                File dir = new File(path.getAbsolutePath() + "/Collection Tracker/");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(dir, System.currentTimeMillis() + ".jpg");
                try {
                    outputStream = new FileOutputStream(file);
                    Log.i("Tag", "hello");
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(file));
                    sendBroadcast(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(GenerateQR.this, "Image Saved Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


