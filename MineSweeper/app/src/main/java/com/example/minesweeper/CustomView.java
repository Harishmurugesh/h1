package com.example.minesweeper;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {

    public final Paint paint = new Paint() ;
    public final Paint paint2 = new Paint();
    public final Paint paint3 = new Paint() ;
    public final Paint paint4 = new Paint() ;
    public final Paint paint5 = new Paint() ;
    public int cellsize = getWidth()/8;
    public float x = 0 , y = 0;
    public int row = 1 ,col = 1 ;
    public final GameLogic game;
    public int count1 = 0 , count2 = 0 , count3 = 0 , count4 = 0;
    public int[] p;
    public int[] q;
    public int s =0;
    public int t =0;
    public int[][] n;
    public int r = 0 ;
    public int score = 0;
    public Bitmap bomb;

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        game = new GameLogic();
        p = new int[500];
        q = new int[500];
        n = new int[8][8];
        bomb = BitmapFactory.decodeResource(getResources(), R.drawable.bomb);


        n = game.getGameBoard();

    }


    protected  void onMeasure(int width , int height ){
        super.onMeasure(width , height);

        int dimension = Math.min(getMeasuredWidth() , getMeasuredHeight());
        cellsize = dimension/8;
        setMeasuredDimension(dimension , dimension);
    }




    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint2.setTextSize(75);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.BLACK);
        paint2.setStyle(Paint.Style.FILL);
        paint3.setAntiAlias(true);
        paint3.setColor(Color.BLUE);
        paint4.setAntiAlias(true);
        paint4.setColor(Color.WHITE);
        paint5.setAntiAlias(true);
        paint5.setColor(Color.RED);


        for(int v = 0 ; v <8 ; v++){
            for(int u = 0 ; u <8 ; u++)
                canvas.drawRect(v * cellsize, u * cellsize, (v + 1) * cellsize, (u + 1) * cellsize, paint3);
        }

        if(count1!=0){
            for(int o = 0 ; o < r ;o++){
                canvas.drawRect((q[o] - 1) * cellsize, (p[o] - 1) * cellsize, q[o] * cellsize, p[o] * cellsize, paint4);
                canvas.drawText(Integer.toString(n[q[o] - 1][p[o] - 1]), (float) (q[o] - 0.6) * cellsize, (float) (p[o] - 0.4) * cellsize, paint2);

            }}

        if(count4 == 1){
            for(int k =0 ; k < game.i ; k++){
                canvas.drawRect((game.x[k]) * cellsize,(game.y[k]) * cellsize, (game.x[k] +1 ) * cellsize, (game.y[k] + 1) * cellsize, paint5);
                canvas.drawBitmap(bomb , (float) (game.x[k] + 0.3)*cellsize , (float) (game.y[k] + 0.3)*cellsize , null);
            }}


        drawBoard(canvas);
    }




    protected  void drawBoard(Canvas canvas){
        paint.setStrokeWidth(16);

        for(int a=0 ; a<9 ; a++){
            canvas.drawLine(cellsize*a , 0 , cellsize*a , canvas.getWidth() , paint);
        }

        for(int b=0 ; b<9 ; b++){
            canvas.drawLine(0 , cellsize*b , canvas.getWidth() , cellsize*b , paint);
        }
        invalidate();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = event.getX();
        y = event.getY();

        if((count2==0)&&(count4==0)) {
            int action = event.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                row = (int) Math.ceil(y / cellsize);
                col = (int) Math.ceil(x / cellsize);

                upgradeBoard(game.getGameBoard(), row, col);
                return true;
            }
        }
        return false;
    }



    public void upgradeBoard(int a[][] , int b , int c) {


        if (a[c - 1][b - 1] != 100){
            if(r == 0){
                p[r] = row;
                q[r] = col;
                r++;
                count1 = count1 + 1;
            }
            else{
                for(int ij = 0 ; ij < r ; ij++){
                    if((p[ij]==row)&&(q[ij]==col))
                        count3++;
                }
                if(count3==0){
                    p[r] = row;
                    q[r] = col;
                    r = r + 1;
                    count1 = count1 + 1;
                    score = score + 1;}
                else
                    count3 = 0;
            }}

        else{
            count2 = 1;
            count4 = 1;
            s =row;
            t =col;

        }

        invalidate();
    }
}



