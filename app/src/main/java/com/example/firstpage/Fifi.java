


package com.example.firstpage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;

import com.example.firstpage.tita.FloodFill;


public class Fifi extends View {
    private float brushSize, lastBrushSize;
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
        boolean erase = false;
        path = new Path();
        paint = new Paint();

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10f);

    }
    public static Bitmap changeBitmapColor(Bitmap sourceBitmap, int color)
    {
        Bitmap resultBitmap = sourceBitmap.copy(sourceBitmap.getConfig(),true);
        Paint paint = new Paint();
        ColorFilter filter = new LightingColorFilter(color, 1);
        paint.setColorFilter(filter);
        Canvas canvas = new Canvas(resultBitmap);
        canvas.drawBitmap(resultBitmap, 0, 0, paint);
        return resultBitmap;
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

      //  canvas.drawBitmap(bitmap,0,0,null);
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

        }

         return true;
    }

    private void paint(int x, int y) {
        int targetColor=bitmap.getPixel(x,y);
        FloodFill.flootFill(bitmap,new Point(x,y),targetColor,Common.COLOR_SELECTED);
        invalidate();
    }

    }


