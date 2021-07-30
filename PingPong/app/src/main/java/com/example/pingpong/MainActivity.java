package com.example.pingpong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.PublicKey;

import Views.CustomView;

import static android.view.View.INVISIBLE;

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
    public int time = 25 ;
    public int flag = 0;
    public int shoe = 0;
    public int socks = 0;
    public MediaPlayer mediaPlayer = new MediaPlayer();


    public View.OnClickListener clickListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {

                case R.id.button2:{
                    shoe = 1;
                    socks = 1;
                }break;
                case R.id.button3:{
                    shoe = 2;
                    socks = 1;
                }break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        customView = (CustomView) findViewById(R.id.customView);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        t4 = (TextView) findViewById(R.id.textView4);
        t5 = (TextView) findViewById(R.id.textView5);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b2.setOnClickListener(clickListener1);
        b3.setOnClickListener(clickListener1);
        customView.setVisibility(INVISIBLE);
        customView.orientation = this.getResources().getConfiguration().orientation;
        if(customView.orientation == Configuration.ORIENTATION_PORTRAIT)
            customView.ori = 1;
        else
            customView.ori = 2;

        SharedPreferences settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        mediaPlayer = MediaPlayer.create(this,R.raw.over);
        final MediaPlayer mediaPlayer2 = MediaPlayer.create(this,R.raw.dashes);
        final MediaPlayer mediaPlayer3 = MediaPlayer.create(this,R.raw.won);
        final MediaPlayer mediaPlayer4 = MediaPlayer.create(this , R.raw.games);
        final MediaPlayer mediaPlayer5 = MediaPlayer.create(this , R.raw.powerup);



        Thread thread4 = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(15000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customView.powerup();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread3 = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(3500);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                time = time - 1;
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        Thread thread2 = new Thread() {
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(time);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if(shoe == 1)
                                { customView.move2(); b2.setVisibility(INVISIBLE); b3.setVisibility(INVISIBLE); customView.setVisibility(View.VISIBLE);
                                    if(socks == 1){
                                        mediaPlayer4.start();
                                        socks = 0;
                                    }
                                }
                                else if(shoe == 2)
                                { customView.move1(); b2.setVisibility(INVISIBLE); b3.setVisibility(INVISIBLE); customView.setVisibility(View.VISIBLE);
                                    if(socks == 1){
                                        mediaPlayer4.start();
                                        socks = 0;
                                    }
                                }
                                t5.setText("Score :" + customView.count1);
                                t4.setText("Score : " + customView.count2);
                                int highScore = settings.getInt("HIGH_SCORE", 0);
                                if (customView.cy > 2100) {
                                    t1.setText(" SCORE :" + customView.count1);
                                    if (customView.count1 >= highScore) {
                                        t3.setText("HIGH SCORE :" + customView.count1);
                                        editor.putInt("HIGH_SCORE", customView.count1);
                                        editor.commit();
                                    } else {
                                        t3.setText("HIGH SCORE :" + highScore);
                                    }
                                    t2.setText("YOU LOST");
                                    t1.setVisibility(View.VISIBLE);
                                    t2.setVisibility(View.VISIBLE);
                                    t3.setVisibility(View.VISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    customView.count3 = 1;
                                    if (flag == 0)
                                        mediaPlayer.start();
                                    mediaPlayer4.stop();
                                    flag++;
                                }

                                if(customView.cy < customView.y5){
                                    t1.setText(" SCORE :" + customView.count1);
                                    if (customView.count1 >= highScore) {
                                        t3.setText("HIGH SCORE :" + customView.count1);
                                        editor.putInt("HIGH_SCORE", customView.count1);
                                        editor.commit();
                                    } else {
                                        t3.setText("HIGH SCORE :" + highScore);
                                    }
                                    t2.setText("YOU WON");
                                    t1.setVisibility(View.VISIBLE);
                                    t2.setVisibility(View.VISIBLE);
                                    t3.setVisibility(View.VISIBLE);
                                    b1.setVisibility(View.VISIBLE);
                                    customView.count3 = 1;
                                    if (flag == 0)
                                        mediaPlayer3.start();
                                    mediaPlayer4.stop();
                                    flag++;
                                }

                                if((customView.cy > 0)&&(customView.cy <customView.y4)) {
                                    if (customView.cx > customView.x2) {
                                        mediaPlayer2.start();
                                    }
                                    if (customView.cx < customView.x1) {
                                        mediaPlayer2.start();
                                    }
                                    if (customView.cy == (customView.y1 - customView.c)) {
                                        if ((customView.cx > (customView.rect2.left)) && (customView.cx < (customView.rect2.right))) {
                                            mediaPlayer2.start();
                                        }
                                    }
                                    if ((customView.cy > customView.y2) && (customView.cy < customView.y3)) {
                                        if ((customView.cx > (customView.rect1.left)) && (customView.cx < (customView.rect1.right))) {
                                            mediaPlayer2.start();
                                        }
                                    }

                                    if((customView.count6!=0)||(customView.count7!=0)){
                                        mediaPlayer5.start();
                                        customView.count6 = 0;
                                        customView.count7 = 0;
                                    }
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread2.start();
        thread3.start();
        thread4.start();




    }

    public void tryAgain(View view){

        customView.cx = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);
        customView.cy = customView.z;
        if(customView.b != customView.e )
            customView.b =  customView.e;
        customView.count1 = 0;
        customView.count2 = 0;
        t1.setText("");
        t1.setVisibility(INVISIBLE);
        t2.setVisibility(INVISIBLE);
        t3.setVisibility(INVISIBLE);
        b1.setVisibility(INVISIBLE);
        time = 25 ;
        customView.count3 = 0;
        customView.count4 = 0;
        customView.count5 = 0;
        customView.rect1.left = 0;
        customView.rect1.right = 4 * customView.rect_breadth;
        customView.v = 2 * customView.rect_breadth;
        flag = 0;
        customView.count6 = 0;
        customView.count7 = 0;
        socks = 0;
        mediaPlayer.stop();



    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);


        outState.putInt("numclick1" , (customView.cx));
        outState.putInt("numclick2" , (customView.cy)/customView.getHeight());
        outState.putInt("numclick3",customView.count1);
        outState.putInt("numclick4", customView.count2);
        outState.putInt("numclick5", customView.count3);
        outState.putInt("numclick6", customView.count4);
        outState.putInt("numclick7", customView.count5);
        outState.putInt("numclick8", flag);
        outState.putInt("numclick9" , shoe);
        outState.putInt("numclick10" , customView.a);
        outState.putInt("numclick11" , customView.b);
        outState.putInt("numclick12" , customView.cy2);
        outState.putInt("numclick13" , customView.cy3);
        outState.putInt("numclick14" , customView.cx2);
        outState.putInt("numclick15" , customView.cx3);
        outState.putInt("numclick16" , customView.count6);
        outState.putInt("numclick17" , customView.count7);
        outState.putInt("numclick18" , socks);

        shoe=0;
        socks=0;

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);

        customView.cx =savedInstanceState.getInt("numclick1");
        customView.cy = (savedInstanceState.getInt("numclick2"))*customView.getHeight() ;
        customView.count1 = savedInstanceState.getInt("numclick3");
        customView.count2 = savedInstanceState.getInt("numclick4");
        customView.count3 = savedInstanceState.getInt("numclick5");
        customView.count4 = savedInstanceState.getInt("numclick6");
        customView.count5 = savedInstanceState.getInt("numclick7");
        flag = savedInstanceState.getInt("numclick8");
        shoe = savedInstanceState.getInt("numclick9");
        customView.a = savedInstanceState.getInt("numclick10");
        customView.b = savedInstanceState.getInt("numclick11");
        customView.cy2 = savedInstanceState.getInt("numclick12");
        customView.cy3 = savedInstanceState.getInt("numclick13");
        customView.cx2 = savedInstanceState.getInt("numclick14");
        customView.cx3 = savedInstanceState.getInt("numclick15");
        customView.count6 = savedInstanceState.getInt("numclick16");
        customView.count7 = savedInstanceState.getInt("numclick17");
        socks = savedInstanceState.getInt("numclick18");


    }
}


