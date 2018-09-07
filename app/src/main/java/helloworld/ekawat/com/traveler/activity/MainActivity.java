package helloworld.ekawat.com.traveler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import helloworld.ekawat.com.traveler.R;

public class MainActivity extends AppCompatActivity {

    private static ImageView imgView;
    private static Button btnChange;
    private int current_image;
    int[] images = {R.drawable.bg_1, R.drawable.bg_2, R.drawable.bg_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttononclick();
    }

    private void buttononclick() {
        btnChange = (Button) findViewById(R.id.btnChange);
        imgView = (ImageView) findViewById(R.id.imgView);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_image++;
                current_image =current_image % images.length;
                imgView.setImageResource(images[current_image]);
            }
        });
    }
}
