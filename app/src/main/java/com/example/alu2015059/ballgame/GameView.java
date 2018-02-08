package com.example.alu2015059.ballgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alu2015059 on 05/02/2018.
 */

public class GameView extends View{

    protected Paint paint;
    protected Handler handler;

    public GameView(Context context) {this(context, null, 0);}
    public GameView(Context context, @Nullable AttributeSet attrs){this(context, attrs, 0);}
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1.0f);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(50);
        handler = new Handler();
        handler.postDelayed(runnable, 50);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(runnable, 50);
            GameView.this.updatePhisics();
            GameView.this.invalidate();
        }
    };

    @Override public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        int action = event.getAction();
        if(action != MotionEvent.ACTION_DOWN) return true;
        int w = getWidth();
        int h = getHeight();
        //Movimiento izquierda derecha
        if(x < w / 3) this.ax--;
        else if(x > 2 * w/3) this.ax++;
        //Movimiento arriba abajo
        if(y < h / 3) this.ay--;
        else if(y > 2 * h/3) this.ay++;
        this.invalidate();
        return true;
    }

    private static final int RADIUS = 10;
    private static final float FRICTION = 0.97f;
    private float x, y, vx, vy, ax, ay;

    public void update(float ax, float ay){
        this.ax = ax;
        this.ay = ay;
    }

    public void updatePhisics(){
        float deltaT = 0.050f;
        vx += 10 * ax * deltaT;
        vy += 10 * ay * deltaT;
        vx *= FRICTION;
        vy *= FRICTION;
        x += vx * deltaT;
        y += vy * deltaT;
        int w = getWidth();
        int h = getHeight();
        if(x < -w /2 + RADIUS) vx = -vx;
        if(x > w / 2 - RADIUS) vx = -vx;
        if(y < -h / 2 + RADIUS) vy = -vy;
        if(y > h / 2 - RADIUS) vy = -vy;
    }

    @Override public void onDraw(Canvas canvas){
        canvas.drawColor(Color.LTGRAY);
        int w =  getWidth();
        int h = getHeight();
        canvas.translate(w/2, h/2); //Ponemos la bola en medio
        canvas.drawCircle(x, y, RADIUS, paint); //Creamos la bola
        String text = "Ax = " + ax + "Ay = " + ay;
        canvas.drawText(text, -w/2, h/2 - 10, paint);
    }
}
