package com.example.minesweeper;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public CustomView customView;
    public TextView t1;
    public TextView t2;
    public TextView t3;
    public TextView t4;
    public TextView t5;
    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Vibrator vibrator;
    public int count3 = 0;
    public int time = 180;
    public int flag = 0;


    public View.OnClickListener clickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.button:{
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    customView.game.func4();
                    customView.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    flag =1;
                    time = 180;
                }break;
                case R.id.button2:{
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    customView.game.func5();
                    customView.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    flag =1;
                    time = 120;
                }break;
                case R.id.button3:{
                    b1.setVisibility(View.INVISIBLE);
                    b2.setVisibility(View.INVISIBLE);
                    b3.setVisibility(View.INVISIBLE);
                    customView.game.func6();
                    customView.setVisibility(View.VISIBLE);
                    t2.setVisibility(View.VISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    flag =1;
                    time = 60;
                }break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b1.setOnClickListener(clickListener1);
        b2.setOnClickListener(clickListener1);
        b3.setOnClickListener(clickListener1);
        customView = (CustomView) findViewById(R.id.customView);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        customView.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        b4.setVisibility(View.INVISIBLE);

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();




        Thread thread2 = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(flag==1){
                                    time--;
                                    t5.setText("Time = "+ (time/60) +" : "+ (time%60));
                                    t5.setVisibility(View.VISIBLE);
                                }}
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread2.start();



        Thread thread = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(25);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                t2.setText("Score : " + customView.score);
                                int highScore = settings.getInt("HIGH_SCORE", 0);
                                if (customView.count2 == 1) {
                                    if (customView.score >= highScore) {
                                        t3.setText("HIGH SCORE :" + customView.score);
                                        t3.setVisibility(View.VISIBLE);
                                        editor.putInt("HIGH_SCORE", customView.score);
                                        editor.commit();
                                    } else {
                                        t3.setText("HIGH SCORE :" + highScore);
                                        t3.setVisibility(View.VISIBLE);
                                    }
                                    if (count3 == 0) {
                                        vibrator.vibrate(1000);
                                        count3++;
                                    }
                                    t1.setText("YOU LOST");
                                    t1.setVisibility(View.VISIBLE);
                                    b4.setVisibility(View.VISIBLE);
                                    flag = 0;
                                } else if (customView.r == (64 - customView.game.i)) {
                                    if (customView.score >= highScore) {
                                        t3.setText("HIGH SCORE :" + customView.score);
                                        t3.setVisibility(View.VISIBLE);
                                        editor.putInt("HIGH_SCORE", customView.score);
                                        editor.commit();
                                    } else {
                                        t3.setText("HIGH SCORE :" + highScore);
                                        t3.setVisibility(View.VISIBLE);
                                    }
                                    t1.setText("YOU WON");
                                    customView.count4 = 1;
                                    t1.setVisibility(View.VISIBLE);
                                    b4.setVisibility(View.VISIBLE);
                                    flag =0;
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();



    }

    public void homePage(View view){

        b4.setVisibility(View.INVISIBLE);
        customView.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        count3 = 0;
        customView.x =0;
        customView.y =0;
        customView.row =1;
        customView.col =1;
        customView.r =0;
        customView.s = 0;
        customView.t = 0;
        customView.count1 =0;
        customView.count2 =0;
        customView.count3 =0;
        customView.count4 =0;
        customView.score =0;
        customView.game.count5 =0;
        t4.setVisibility(View.VISIBLE);
        flag =0;
        time = 180;
    }

    @Override
    public void onBackPressed() {

        b4.setVisibility(View.INVISIBLE);
        customView.setVisibility(View.INVISIBLE);
        t1.setVisibility(View.INVISIBLE);
        t2.setVisibility(View.INVISIBLE);
        t3.setVisibility(View.INVISIBLE);
        t5.setVisibility(View.INVISIBLE);
        b1.setVisibility(View.VISIBLE);
        b2.setVisibility(View.VISIBLE);
        b3.setVisibility(View.VISIBLE);
        count3 = 0;
        customView.x =0;
        customView.y =0;
        customView.row =1;
        customView.col =1;
        customView.r =0;
        customView.s = 0;
        customView.t = 0;
        customView.count1 =0;
        customView.count2 =0;
        customView.count3 =0;
        customView.count4 =0;
        customView.score =0;
        customView.game.count5 =0;
        t4.setVisibility(View.VISIBLE);
        flag =0;
        time = 180;
        //super.onBackPressed();
    }
}