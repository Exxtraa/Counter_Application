package com.example.counterapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView counter_text;
    Button btn;
    int counter = 0; // Start counter from 0
    Handler handler = new Handler();
    Runnable runnable;
    TextView daytext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        counter_text = findViewById(R.id.counter_text);
        daytext = findViewById(R.id.daytext);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        String dayName = days[day -1];

        daytext.setText("Today is "+ dayName);

        // Runnable to increase the counter every second
        runnable = new Runnable() {
            @Override
            public void run() {
                counter_text.setText(counter + " sec");
                counter++;
                handler.postDelayed(runnable, 1000); // Repeat every second
            }
        };
        handler.post(runnable); // Start the counter

        // Button click listener to reset the counter
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter = 1; // Reset counter to 1
                counter_text.setText(counter + " sec");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Remove callbacks when activity is destroyed
    }
}
