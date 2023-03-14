package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class MediumCar {
    Bitmap medium[] = new Bitmap[3];
    int vehicleFrame = 0;
    int vehicleX, vehicleY, vehicleVelocity;
    Random random;

    public MediumCar(int x, int y, int veloc) {
        vehicleX = x;
        vehicleY = y;
        vehicleVelocity = veloc;
    }
    public MediumCar(Context context) {
        medium[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
        medium[0] = Bitmap.createScaledBitmap(medium[0], medium[0].getWidth() * 2, medium[0].getHeight() * 2, false);
        medium[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
        medium[1] = Bitmap.createScaledBitmap(medium[1], medium[1].getWidth() * 2, medium[1].getHeight() * 2, false);
        medium[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
        medium[2] = Bitmap.createScaledBitmap(medium[2], medium[2].getWidth() * 2, medium[2].getHeight() * 2, false);
        random = new Random();
        resetPosition();
    }

    public Bitmap getMediumCar(int carFrame) {
        return medium[carFrame];
    }

    public int getMediumCarWidth() {
        return medium[0].getWidth();
    }

    public int getMediumCarHeight() {
        return medium[0].getHeight();
    }
    public void resetPosition() {
        vehicleX = GameView.getDeviceWidth() + getMediumCarWidth();
        vehicleY = 1534;
        vehicleVelocity = 5; //+ random.nextInt(16);
    }

    public void resetPosition2(int width) {
        vehicleX = GameView.getDeviceWidth() + width;
        vehicleY = 1338;
        vehicleVelocity += 5; //+ random.nextInt(16);
    }

    public void resetPosition3(int width) {
        vehicleX = 0 - width;
        vehicleY = 1240;
        vehicleVelocity = 1;
    }

    public void accelerate() {
        vehicleVelocity++;
    }

}
