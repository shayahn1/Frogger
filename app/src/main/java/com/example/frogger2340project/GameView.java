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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    private Bitmap background;
    private Bitmap frog;
    private Rect rectBackground;
    private Context context;
    private Handler handler;
    private final long updateMillis = 30;
    private Runnable runnable;
    private Paint textPaint = new Paint();
    private Paint healthPaint = new Paint();
    private float textSize = 80;
    private int lives;
    private int score;
    //private float frogX;
    private float oldFrogX;
    private float oldX;
    //private float frogY;
    private static int deviceWidth;
    private static int deviceHeight;
    private String globalDifficulty;
    Frog newFrog; // new Frog(deviceWidth / 2 - frog.getWidth() / 2, deviceHeight - 200 - frog.getHeight() / 2);

    public GameView(Context context, String difficulty, String spriteSelected) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_screen);
        globalDifficulty = difficulty;
        switch (spriteSelected) {
        case "G":
            frog = BitmapFactory.decodeResource(getResources(), R.drawable.greenfrog);
            break;
        case "B":
            frog = BitmapFactory.decodeResource(getResources(), R.drawable.bluefrog);
            break;
        case "P":
            frog = BitmapFactory.decodeResource(getResources(), R.drawable.purplefrog);
            break;
        default:
            break;
        }
        frog = Bitmap.createScaledBitmap(frog, frog.getWidth() * 2, frog.getHeight() * 2, false);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;
        deviceHeight = size.y;
        rectBackground = new Rect(0, 0, deviceWidth, deviceHeight);
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
        //frogX = deviceWidth / 2 - frog.getWidth() / 2;
        //frogY = deviceHeight - 200 - frog.getHeight() / 2;
        newFrog = new Frog(deviceWidth / 2 - frog.getWidth() / 2, deviceHeight - 200 - frog.getHeight() / 2);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(frog, newFrog.getFrogX(), newFrog.getFrogY(), null);
        if (globalDifficulty.equals("Easy")) {
            lives = 3;
        } else if (globalDifficulty.equals("Medium")) {
            lives = 2;
        } else {
            lives = 1;
        }
        canvas.drawText(globalDifficulty, 20, textSize, textPaint);
        canvas.drawText("Lives: " + lives, deviceWidth / 2, textSize, textPaint);
        canvas.drawText("Score: " + score, deviceWidth / 2, textSize * 2, textPaint);
        canvas.drawText(PlayerConfig.getPlayerName(), 20, textSize * 2, textPaint);
        handler.postDelayed(runnable, updateMillis);
    }

    public String getGlobalDifficulty() {
        return globalDifficulty;
    }

    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (touchY < newFrog.getFrogY()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogY = newFrog.getFrogY() - frog.getHeight();
                newFrog.moveFrogUp(newFrogY);
            }
        }
        else if (touchY > newFrog.getFrogY() + frog.getHeight()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogY = newFrog.getFrogY() + frog.getHeight();
                newFrog.moveFrogDown(newFrogY);
            }
        }
        else if (touchX > newFrog.getFrogX() + frog.getWidth()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogX = newFrog.getFrogX() + frog.getWidth();
                newFrog.moveFrogRight(newFrogX);
            }
        }
        else if (touchX < newFrog.getFrogX()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogX = newFrog.getFrogX() - frog.getWidth();
                newFrog.moveFrogLeft(newFrogX);
            }
        }
        return true;
    }
}
