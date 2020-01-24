package com.punitexample.collection_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class GenerateQR extends AppCompatActivity {

    ImageView qrCode;
    String input;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    EditText editText;
    Button convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        convert = (Button) findViewById(R.id.Convert);
        qrCode = (ImageView) findViewById(R.id.qrCode);
        editText = (EditText) findViewById(R.id.editText);




        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input = editText.getText().toString().trim();
                if (input.length() > 0) {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    editText.setError("Required!");
                }
            }
        });
    }
}
