package com.example.textview;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.appcompat.app.AppCompatActivity;


/*public class TodayExercise extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
    }
}*/

public class TodayExercise extends AppCompatActivity implements SensorEventListener {
    private TextView stepCountTextView;
    private SensorManager sensorManager;
    private Sensor stepCountSensor;
    private int stepCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 1);
        }
        // UI 요소 가져오기
        stepCountTextView = findViewById(R.id.step_count_text_view);
        // 센서 매니저와 걸음 수 센서 가져오기
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        stepCountSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        // 센서가 지원되는 경우
        if (stepCountSensor != null) {
            // 센서 리스너 등록
            sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
            stepCount = 0;
        } else {
            Toast.makeText(this, "걸음 수 센서가 지원되지 않습니다.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 센서 값이 변경된 경우
        if (event.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            // 걸음 수 업데이트
            stepCount++;
            // UI 업데이트
            stepCountTextView.setText("걸음 수: " + stepCount);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 정확도가 변경된 경우 (여기서는 처리하지 않음)
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 센서 리스너 해제
        sensorManager.unregisterListener(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (stepCountSensor != null) {
            sensorManager.registerListener(this, stepCountSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

}
