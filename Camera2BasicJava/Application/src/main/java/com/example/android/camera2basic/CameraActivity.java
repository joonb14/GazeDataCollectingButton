/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.camera2basic;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CameraActivity extends AppCompatActivity {

    private SensorManager mSensorManager;

    private Sensor mGyroSensor = null;
    private Sensor mAccelerometer = null;

    private static double gyroX;
    private static double gyroY;
    private static double gyroZ;

    private  static double accX;
    private  static double accY;
    private  static double accZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MOBED
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int densityDpi = (int)(metrics.density * 160f);
        Log.d("MOBED","DPI: "+Integer.toString(densityDpi));

        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mGyroSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(gyroListener, mGyroSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(acceleroListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(gyroListener);
        mSensorManager.unregisterListener(acceleroListener);
    }
    public SensorEventListener gyroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor mGyroSensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            gyroX = event.values[0];
            gyroY = event.values[1];
            gyroZ = event.values[2];
            //SLog.d("MOBED","Gyro: "+gyroX+ " "+gyroY+" "+gyroZ+"rad/s");
        }
    };
    public SensorEventListener acceleroListener = new SensorEventListener() {
        public void onAccuracyChanged(Sensor mGyroSensor, int acc) {
        }

        public void onSensorChanged(SensorEvent event) {
            accX = event.values[0];
            accY = event.values[1];
            accZ = event.values[2];
            //Log.d("MOBED","Accelerometer: "+accX+ " "+accY+" "+accZ+"m/s^2");
        }
    };
    public static String getGyroData(){
        return gyroX+ "_"+gyroY+"_"+gyroZ;
    }
    public static String getAcceleroData(){
        return accX+ "_"+accY+"_"+accZ;
    }
}
