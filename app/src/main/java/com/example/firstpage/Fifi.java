


package com.example.firstpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;


public class Fifi extends View {

    Context context;
    int width, height;
    Bitmap bitmap;
    Paint paint;
    Path path;
    static Canvas canvas;
    float mx, my;
    static final float TOLERANCE = 4;
    public Paint getPaint() {return paint;}

    public Fifi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        path = new Path();
        paint = new Paint();

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);


    }

    public Fifi(Object bitmap) {
        super((Context) bitmap);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

    }

    public void startTouch(float x, float y) {
        path.moveTo(x, y);
        mx = x;
        my = y;
    }

    public void moveTouch(float x, float y) {
        float dx = Math.abs(x - mx);
        float dy = Math.abs(y - my);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            path.quadTo(mx, my, (x + mx) / 2, (y + my) / 2);
            mx = x;
            my = y;
        }
    }

    public void clearCanvas() {
        path.reset();
        invalidate();
    }

    public void upTouch() {
        path.lineTo(mx, my);

    }

    @Override

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }return true;

    }

}

