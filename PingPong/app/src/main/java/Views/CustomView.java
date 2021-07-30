package Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.pingpong.R;
import com.example.pingpong.R;

public class CustomView extends View {

    private int breadth = 100;
    private int radius = 40 ;
    public int y3 = 20 ;
    public int z = 260 , w = 500;
    private int plus = 20;
    public Rect rect1;
    public Rect rect2;
    private Paint paintrect1;
    private Paint paintrect2;
    private Paint paintcircle1;
    private Paint paintcircle2;
    private Paint paintcircle3;
    private Paint paint;
    public int x1 = 0 , y1 = 0 ;
    public float x2 = 0f ;
    public int y2 , x3 , y4 , y5 , y6 , y7;
    public int x , y;
    public int orientation;
    public int ori = 1;

    public int count1 = 0 , count2 = 0 , count3 = 0 , count4 = 0 , count5 = 0 , count6 = 0 , count7 = 0;
    public int rect_breadth , circle_radius , a,b,cx,cy,c,d,e,v;
    public int cx2 = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);
    public int cx3 = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);
    public int cy2 , cy3;


    public CustomView(Context context) {
        super(context);
        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void  init(@Nullable AttributeSet set){

        paintcircle1 = new Paint();
        paintcircle1.setAntiAlias(true);
        paintcircle1.setColor(Color.GREEN);
        paintcircle2 = new Paint();
        paintcircle2.setAntiAlias(true);
        paintcircle2.setColor(Color.YELLOW);
        paintcircle3 = new Paint();
        paintcircle3.setAntiAlias(true);
        paintcircle3.setColor(Color.MAGENTA);
        paintrect1 = new Paint();
        paintrect2 = new Paint();
        paintrect1.setAntiAlias(true);
        paintrect1.setColor(Color.RED);
        paintrect2.setAntiAlias(true);
        paintrect2.setColor(Color.BLUE);
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        rect1 = new Rect();
        rect2 = new Rect();

        if (set == null)
            return;

        TypedArray ta = getContext().obtainStyledAttributes(set , R.styleable.CustomView);

        rect_breadth = ta.getDimensionPixelSize(R.styleable.CustomView_breadth,breadth);
        circle_radius = ta.getDimensionPixelSize(R.styleable.CustomView_radius,radius);
        a = ta.getDimensionPixelSize(R.styleable.CustomView_a,plus);
        b = ta.getDimensionPixelSize(R.styleable.CustomView_b,plus);
        e = ta.getDimensionPixelSize(R.styleable.CustomView_e,plus);
        cx = ta.getDimensionPixelSize(R.styleable.CustomView_cx,w);
        cy = ta.getDimensionPixelSize(R.styleable.CustomView_cy,z);
        cy2 = ta.getDimensionPixelSize(R.styleable.CustomView_cy2,z);
        cy3 = ta.getDimensionPixelSize(R.styleable.CustomView_cy3,z);
        c = ta.getDimensionPixelSize(R.styleable.CustomView_c,y3);
        d = ta.getDimensionPixelSize(R.styleable.CustomView_d,plus);
        v = ta.getDimensionPixelOffset(R.styleable.CustomView_v,2*rect_breadth);
        ta.recycle();

        rect1.left = 0;
        rect1.right = 4 * rect_breadth;
        rect2.left = 0 ;
        rect2.right = 4 * rect_breadth;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        x3 = getWidth();
        y4 = getHeight();
        x1 = circle_radius ;
        x2 = x3 - circle_radius;
        y5 = 0;
        y6 = y4 + 8 * circle_radius;
        y1 = 6 * circle_radius + c;

        if(ori == 1){

            y2 = y4 - circle_radius - (2 * rect_breadth) -c ;
            y3 = y4 - (2 * rect_breadth) - circle_radius;

            rect1.top= y4 -(2 * rect_breadth) - c ;
            rect1.bottom = y4 - rect_breadth - c;
            rect2.top = 3 * circle_radius;
            rect2.bottom = 3 * circle_radius + rect_breadth;
            y7 = rect2.bottom + circle_radius;
        }

        if(ori == 2){

            y2 = y4 - rect_breadth - circle_radius -c;
            y3 = y4 - rect_breadth - circle_radius;

            rect1.top = y4 - rect_breadth;
            rect1.bottom = y4;
            rect2.top = 0 ;
            rect2.bottom = rect_breadth;
            y7 = rect2.bottom + circle_radius;
        }








        count5++;


        canvas.drawCircle(cx , cy , circle_radius, paintcircle1);

        if(count5 > 200) {
            canvas.drawCircle(cx2, cy2, c, paintcircle2);
            canvas.drawCircle(cx3, cy3, c, paintcircle3);
        }
        canvas.drawRect(rect1, paintrect1);
        canvas.drawRect(rect2, paintrect2);
        canvas.drawLine(0,0,0 , 3000 , paint);
        canvas.drawLine(x3,0,x3,3000,paint);

    }

    public void move1() {
        count4++;
        if(cx > x2){a=-a;}
        if(cx < x1){a=-a;}
        if(cy == (y7-c)){
            if((cx > (rect2.left))&&(cx < (rect2.right))){
                b = -b;
                count2++;
            }
        }
        if((cy > y2)&&(cy < y3)){
            if((cx > (rect1.left))&&(cx < (rect1.right)))
            { b=-b;
                count1++; }
        }

        cx = cx + a;
        cy = cy + b;
        if(count4 > 200){
            cy2 = cy2 + e/2;
            cy3 = cy3 + e/2;}


        rect2.right = cx + (2 * rect_breadth);
        rect2.left = cx - (2 * rect_breadth);

        if((cy2 >y2)&&(cy2 < y3))
        {
            if((cx2 > (rect1.left))&&(cx2 < (rect1.right)))
            {
                rect1.left = rect1.left - c;
                rect1.right = rect1.right + c;
                v = (rect1.right - rect1.left)/2 ;
                count6++;
            }
        }

        if((cy3 > y2)&&(cy3 < y3))
        {
            if((cx3 > (rect1.left))&&(cx3 < (rect1.right)))
            {
                rect1.left = rect1.left + c;
                rect1.right = rect1.right - c;
                v = (rect1.right - rect1.left)/2 ;
                count7++;
            }
        }



        invalidate();
    }

    public void move2() {
        count4++;
        if(cx > x2){a=-a;}
        if(cx < x1){a=-a;}
        if(cy == (y7-c)){
            if((cx > (rect2.left))&&(cx < (rect2.right))){
                b = -b;
                count2++;
            }
        }
        if((cy > y2)&&(cy < y3)){
            if((cx > (rect1.left))&&(cx < (rect1.right)))
            { b=-b;
                count1++; }
        }

        cx = cx + a;
        cy = cy + b;
        if(count4 > 200){
            cy2 = cy2 + e/2;
            cy3 = cy3 + e/2;}

        if(count2 < 8) {
            rect2.right = cx + (2 * rect_breadth);
            rect2.left = cx - (2 * rect_breadth);
        }

        if((cy2 >y2)&&(cy2 < y3))
        {
            if((cx2 > (rect1.left))&&(cx2 < (rect1.right)))
            {
                rect1.left = rect1.left - c;
                rect1.right = rect1.right + c;
                v = (rect1.right - rect1.left)/2 ;
            }
        }

        if((cy3 > y2)&&(cy3 < y3))
        {
            if((cx3 > (rect1.left))&&(cx3 < (rect1.right)))
            {
                rect1.left = rect1.left + c;
                rect1.right = rect1.right - c;
                v = (rect1.right - rect1.left)/2 ;
            }
        }



        invalidate();
    }


    public void powerup(){

        if(count3 == 0) {
            cx2 = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);
            cx3 = (int) Math.floor((Math.random() * (150 + 700 - 25)) + 150);
            cy2 = 7 * circle_radius;
            cy3 = 7 * circle_radius;
            invalidate();
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()== MotionEvent.ACTION_MOVE){
            x = (int) event.getX();
            y = (int) event.getY();
            if ((rect1.left < x)&&(rect1.right > x)){

                rect1.left = x - v;
                rect1.right = x + v;
                postInvalidate();


            }}
        return true;
    }
}


