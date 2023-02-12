package com.example.frogger2340project;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class GameView extends View {

    Bitmap background, frog;
    Rect rectBackground;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float textSize = 120;
    int lives;
    int points;
    float frogX, frogY;
    static int deviceWidth, deviceHeight;

    public GameView(Context context, String difficulty, String spriteSelected) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_screen);
        switch(spriteSelected) {
            case "G":
                frog = BitmapFactory.decodeResource(getResources(), R.drawable.greenfrog);
                break;
            case "B":
                frog = BitmapFactory.decodeResource(getResources(), R.drawable.bluefrog);
                break;
            case "P":
                frog = BitmapFactory.decodeResource(getResources(), R.drawable.purplefrog);
                break;
        }
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;
        deviceHeight = size.y;
        rectBackground = new Rect(0,0, deviceWidth, deviceHeight);
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                invalidate();
            }
        };
        textPaint.setColor(Color.rgb(255, 165, 8));
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.LEFT);
        healthPaint.setColor(Color.GREEN);
        frogX = deviceWidth / 2 - frog.getWidth() / 2;
        frogY = deviceHeight - frog.getHeight();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(frog, frogX, frogY, null);
        canvas.drawText("" + lives, 20, textSize, textPaint);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }
}
