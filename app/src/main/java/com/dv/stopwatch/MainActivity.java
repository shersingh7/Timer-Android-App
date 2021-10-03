package com.dv.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = this.getClass().getCanonicalName();
    private Chronometer chrono;
    private long pauseOffSet;
    private boolean running;
    private Button start;
    private Button pause;
    private Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chrono = findViewById(R.id.timer);
        chrono.setBase(SystemClock.elapsedRealtime());

        start = findViewById(R.id.startButton);
        this.start.setOnClickListener(this);

        pause = findViewById(R.id.pauseButton);
        this.pause.setOnClickListener(this);

        reset = findViewById(R.id.resetButton);
        this.reset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){

        if(v!= null){
            switch (v.getId()){
                case R.id.startButton: {
                    if(!running){
                        chrono.setBase(SystemClock.elapsedRealtime() - pauseOffSet);
                        chrono.start();
                        running = true;
                        Log.d(TAG, "onClick: Start button");
                    }
                    break;
                }

                case R.id.pauseButton: {
                    if(running){
                        chrono.stop();
                        pauseOffSet = SystemClock.elapsedRealtime() - chrono.getBase();
                        running = false;
                        Log.d(TAG, "onClick: pause button");
                    }
                    break;
                }

                case R.id.resetButton:{
                    chrono.setBase(SystemClock.elapsedRealtime());
                    pauseOffSet = 0;
                    Log.d(TAG, "onClick: reset button");
                    break;
                }
            }
        }

    }
/*
    public void startTimer(View v){

    }

    public void pauseTimer(View v){



    }

    public void resetTimer(View v){

    }*/
}