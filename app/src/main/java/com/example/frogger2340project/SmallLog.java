package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SmallLog {
    private Bitmap[] log = new Bitmap[3];
    private int logFrame = 0;
    private int logX;
    private int logY;
    private int logVelocity;

    public SmallLog(Context context) {
        log[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smalllog);
        log[0] = Bitmap.createScaledBitmap(log[0], log[0].getWidth() * 2,
                log[0].getHeight() * 2, false);
        log[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smalllog);
        log[1] = Bitmap.createScaledBitmap(log[1], log[1].getWidth() * 2,
                log[1].getHeight() * 2, false);
        log[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smalllog);
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
        logY = 750;
        logVelocity = 10; //+ random.nextInt(16);
    }

    public void resetPosition2() {
        logX = 0 - getLogWidth();
        logY = 652;
        logVelocity = 2; //+ random.nextInt(16);
    }

    public void resetPosition3() {
        logX = 0 - getLogWidth();
        logY = 554;
        logVelocity = 8; //+ random.nextInt(16);
    }
    public void resetPosition4() {
        logX = GameView.getDeviceWidth() + getLogWidth();
        logY = 456;
        logVelocity = 15; //+ random.nextInt(16);
    }
    public void resetPosition5() {
        logX = 0 - getLogWidth();
        logY = 358;
        logVelocity = 3; //+ random.nextInt(16);
    }

    public void setLogX(int newLogX) {
        logX = newLogX;
    }

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
