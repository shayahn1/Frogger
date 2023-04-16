package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Log {
    private Bitmap[] log = new Bitmap[3];
    private int logFrame = 0;
    private int logX;
    private int logY;
    private int logVelocity;

    public Log(int x, int y, int veloc) {
        logX = x;
        logY = y;
        logVelocity = veloc;
    }
    public Log(Context context) {
        log[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.log);
        log[0] = Bitmap.createScaledBitmap(log[0], log[0].getWidth() * 2,
                log[0].getHeight() * 2, false);
        log[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.log);
        log[1] = Bitmap.createScaledBitmap(log[1], log[1].getWidth() * 2,
                log[1].getHeight() * 2, false);
        log[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.log);
        log[2] = Bitmap.createScaledBitmap(log[2], log[2].getWidth() * 2,
                log[2].getHeight() * 2, false);
        resetPosition();
    }
    public Bitmap getLog(int logFrame) {
        return log[logFrame];
    }

    public int getLogWidth() {
        return log[0].getWidth();
    }

    public int getLogHeight() {
        return log[0].getHeight();
    }

    public void resetPosition() {
        logX = GameView.getDeviceWidth() + getLogWidth();
        logY = 1044;
        logVelocity = 5; //+ random.nextInt(16);
    }

    public void resetPosition2() {
        logX = GameView.getDeviceWidth() + getLogWidth();
        logY = 946;
        logVelocity = 6; //+ random.nextInt(16);
    }

    public void resetPosition3() {
        logX = 0 - getLogWidth();
        logY = 848;
        logVelocity = 5; //+ random.nextInt(16);
    }

    public void setLogX(int newLogX) {
        logX = newLogX;
    }
    public void setLogY(int newLogY) { logY = newLogY; }

    public int getLogFrame() {
        return logFrame;
    }
    public void setLogFrame(int newLogFrame) {
        logFrame = newLogFrame;
    }

    public int getLogX() {
        return logX;
    }
    public int getLogY() {
        return logY;
    }
    public int getLogVelocity() {
        return logVelocity;
    }
}
