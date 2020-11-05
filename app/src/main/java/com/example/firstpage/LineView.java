package com.example.firstpage;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class LineView extends View {
    public LineView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        super.onDraw(canvas);
        int x;
        int y;
        paint.setColor(Color.TRANSPARENT);
        canvas.drawPaint(paint);
        paint.setColor(Color.parseColor("#D3D3D3"));
        paint.setStrokeWidth(7);

        canvas.drawLine(0, 30, 1800, 30, paint);

        canvas.drawLine(0, 130, 1800, 130, paint);

        canvas.drawLine(0, 230, 1800, 230, paint);

        canvas.drawLine(0, 330, 1800, 330, paint);

        canvas.drawLine(0, 430, 1800, 430, paint);

        canvas.drawLine(0,530,1800,530,paint);

        canvas.drawLine(0,630,1800,630,paint);

        canvas.drawLine(0,730,1800,730,paint);

        canvas.drawLine(0,830,1800,830,paint);

        canvas.drawLine(0,930,1800,930,paint);
        canvas.drawLine(0,1030,1800,1030,paint);
        canvas.drawLine(0,1130,1800,1130,paint);
        canvas.drawLine(0,1230,1800,1230,paint);

        canvas.drawLine(80,30,80,2000,paint);

        canvas.drawLine(180,30,180,2000,paint);

        canvas.drawLine(280,30,280,2000,paint);

        canvas.drawLine(380,30,380,2000,paint);

        canvas.drawLine(480,30,480,2000,paint);

        canvas.drawLine(580,30,580,2000,paint);

        canvas.drawLine(680,30,680,2000,paint);

        canvas.drawLine(780,30,780,2000,paint);

        canvas.drawLine(880,30,880,2000,paint);

        canvas.drawLine(980,30,980,2000,paint);





    }}