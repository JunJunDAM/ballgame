package com.example.alu2015059.ballgame;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    GameView gameView;
    SensorManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameView = findViewById(R.id.gameView);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override public void onResume(){
        super.onResume();
        Sensor sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override public void onPause(){
        super.onPause();
        manager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float aX = sensorEvent.values[0];
        float aY = sensorEvent.values[1];
        float aZ = sensorEvent.values[2];
        gameView.update(-aX, aY);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
