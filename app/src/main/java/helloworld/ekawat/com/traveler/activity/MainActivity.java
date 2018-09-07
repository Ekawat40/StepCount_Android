package helloworld.ekawat.com.traveler.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import helloworld.ekawat.com.traveler.R;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {
    private static ImageView imgView;
    private static Button btnChange;
    private int current_image;
    int[] images = {R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3};
    private TextView TvSteps, TvMoney;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); //เข้าถึงSensorของอุปกรณ์ได้
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);




        numSteps = 0;
        sensorManager.registerListener(MainActivity.this, accel, SensorManager.SENSOR_DELAY_FASTEST);

        initInstances();
        buttononclick();
    }

    private void initInstances() {
        TvSteps = (TextView) findViewById(R.id.tv_steps);
        TvMoney = (TextView) findViewById(R.id.tv_money);
        btnChange = (Button) findViewById(R.id.btnChange);
        imgView = (ImageView) findViewById(R.id.imgView);
    }

    private void buttononclick() {
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_image++;
                current_image = current_image % images.length;
                imgView.setImageResource(images[current_image]);
            }
        });
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        TvSteps.setText("" + numSteps);
        TvMoney.setText("" + numSteps);
    }
}
