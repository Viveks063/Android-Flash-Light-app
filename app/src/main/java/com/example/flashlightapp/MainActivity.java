package com.example.flashlightapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.widget.Toast;
import android.widget.Button;

import java.text.BreakIterator;


public class MainActivity extends AppCompatActivity {

    private boolean isFlashlightOn=false;
    private CameraManager cameraManager;
    private String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tb=findViewById(R.id.tb);

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH))
        {
            Toast.makeText(this,"Device does not Support Flasht",Toast.LENGTH_SHORT).show();
            return;
        }

        cameraManager=(CameraManager) getSystemService(CAMERA_SERVICE);

        try {
            cameraId=cameraManager.getCameraIdList()[0];

        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }

        tb.setOnClickListener(v ->
                {
                        if(isFlashlightOn)
                        {
                            turnOffFlash();
                            tb.setText("Turn On");
                        }
                        else {
                            turnOnFlash();
                            tb.setText("Turn off");
                        }
                }
        );
    }

    private void turnOffFlash()
    {
        try {
            cameraManager.setTorchMode(cameraId, false);
            isFlashlightOn=false;
        }
        catch(CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

    private void turnOnFlash()
    {
        try {
            cameraManager.setTorchMode(cameraId, true);
            isFlashlightOn=true;
        }
        catch (CameraAccessException e)
        {
            e.printStackTrace();
        }
    }

}