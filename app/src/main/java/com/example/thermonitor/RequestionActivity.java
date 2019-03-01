package com.example.thermonitor;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RequestionActivity extends AppCompatActivity {

    private int FINE_LOCATION_CODE = 1;
    private int COARSE_LOCATION_CODE =2;
    private int ACCESS_WIFI_CODE = 3;
    private int CHANGE_WIFI_CODE =4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestion);

        Button allowPermission = findViewById(R.id.Requestpermission);
        allowPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)&&
                        (ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)&&
                        (ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_WIFI_STATE)== PackageManager.PERMISSION_GRANTED)&&
                        (ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.CHANGE_WIFI_STATE)== PackageManager.PERMISSION_GRANTED)){

                    Toast.makeText(RequestionActivity.this,"You have already granted this permission", Toast.LENGTH_SHORT).show();

                }
                else{
                    requestPermission();
                }
                Intent intent = new Intent(RequestionActivity.this, DeviceList.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void requestPermission(){

        if(!(ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED)){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(RequestionActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},COARSE_LOCATION_CODE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},COARSE_LOCATION_CODE);
            }
        }
        if(!(ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(RequestionActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_CODE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},FINE_LOCATION_CODE);
            }
        }
        if(!(ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.ACCESS_WIFI_STATE)== PackageManager.PERMISSION_GRANTED)){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_WIFI_STATE)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(RequestionActivity.this, new String[]{Manifest.permission.ACCESS_WIFI_STATE},ACCESS_WIFI_CODE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_WIFI_STATE},ACCESS_WIFI_CODE);
            }
        }
        if(!(ContextCompat.checkSelfPermission(RequestionActivity.this, Manifest.permission.CHANGE_WIFI_STATE)== PackageManager.PERMISSION_GRANTED)){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CHANGE_WIFI_STATE)){

                new AlertDialog.Builder(this)
                        .setTitle("Permission needed")
                        .setMessage("This permission is needed")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(RequestionActivity.this, new String[]{Manifest.permission.CHANGE_WIFI_STATE},CHANGE_WIFI_CODE);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

            }else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CHANGE_WIFI_STATE},CHANGE_WIFI_CODE);
            }
        }

        Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();

    }

}
