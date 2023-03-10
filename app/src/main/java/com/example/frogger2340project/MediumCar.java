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

    public MediumCar(Context context) {
        medium[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
        medium[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
        medium[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.mediumcar);
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
}
