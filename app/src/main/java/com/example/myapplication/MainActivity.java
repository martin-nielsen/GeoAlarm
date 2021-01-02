package com.example.myapplication;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends Activity {
    private int FINE_LOCATION_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("created activity!!!", "The activity was created!");
        setContentView(R.layout.activity_main);

        FloatingActionButton addAlarmButton = findViewById(R.id.floatingActionButton);
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If permission has already been acquired by the app.
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission already acquired!", Toast.LENGTH_SHORT).show();
                } else {
                    // if no permission, request it.
                    requestLocationPermission();
                }
            }
        });

    }

    //TODO: Can this be placed in some sort of service or helper class?
    private void requestLocationPermission() {
//        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            //TODO: Consider calling ActivityCompat#requestPermissions
//            Log.d("Location not acquired::  ", "Permission not acquired");
//
//        } else
        if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
            //TODO: show some sort of dialog to the user. This is probably due to user
            // requesting multiple times
            new AlertDialog.Builder(this)
                    .setTitle("Location Permission Needed")
                    .setMessage("This is needed to use location based alarms efficiently.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[] {ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {ACCESS_FINE_LOCATION}, FINE_LOCATION_PERMISSION_CODE);
//            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

        };
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    private ActivityResultLauncher<String> requestPermissionLauncher =
//            registerForActivityResult(new RequestPermission(), isGranted -> {
//                if (isGranted) {
//
//                } else {
//
//                }
//            });
}