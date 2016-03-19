package com.example.internazionale.gyroscope;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.internazionale.gyroscope.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView tv_value1;
    private TextView tv_value2;
    private TextView tv_value3;
    private TextView tv_value4;
    private SensorManager mSensorManager;
    private Sensor mSensorGyroscope;
    private Sensor mSensorLINEARACCELERATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mSensorManager.registerListener(this, mSensorGyroscope, SensorManager.SENSOR_DELAY_UI);
        bindViews();
    }

    private void bindViews() {
        tv_value1 = (TextView) findViewById(R.id.tv_value1);
        tv_value2 = (TextView) findViewById(R.id.tv_value2);
        tv_value3 = (TextView) findViewById(R.id.tv_value3);
        tv_value4 = (TextView) findViewById(R.id.tv_value4);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float axisX=event.values[0];
        float axisY=event.values[1];
        float axisZ=event.values[2];
        tv_value1.setText("x axis：" + (float) axisX);
        tv_value2.setText("y axis：" + (float) axisY);
        tv_value3.setText("z axis：" + (float) axisZ);
        float total=(float)Math.sqrt(axisX * axisX + axisY * axisY + axisZ*axisZ);
        if(total>0.03) {
            tv_value4.setText("total：" + total);
        }
        else
        {
            tv_value4.setText("total：" + 0);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}