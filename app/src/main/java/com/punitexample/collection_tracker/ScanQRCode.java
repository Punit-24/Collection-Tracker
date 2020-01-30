package com.punitexample.collection_tracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanQRCode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scanner;
    Entity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scanner = new ZXingScannerView(this);
        setContentView(scanner);
        entity = new Entity();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanner.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanner.setResultHandler(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},
                    50); }
        scanner.startCamera();
    }

   /* @Override
    public void onBackPressed() {
        super.onBackPressed();
        Entity.homeIntent = true;
    }*/

    @Override
    public void handleResult(Result result) {
        Entity.serial_num = result.getText();
        Entity.intent = false;
        entity.items();
        onBackPressed();


        /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scanner.resumeCameraPreview(ScanQRCode.this);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();*/
    }
}
