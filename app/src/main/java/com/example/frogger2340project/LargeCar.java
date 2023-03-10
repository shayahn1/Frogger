package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class LargeCar {
    Bitmap large[] = new Bitmap[3];
    int vehicleFrame = 0;
    int vehicleX, vehicleY, vehicleVelocity;
    Random random;

    public LargeCar(Context context) {
        large[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.largecar);
        large[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.largecar);
        large[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.largecar);
        random = new Random();
        resetPosition();
    }

    public Bitmap getLargeCar(int carFrame) {
        return large[carFrame];
    }

    public int getLargeCarWidth() {
        return large[0].getWidth();
    }

    public int getLargeCarHeight() {
        return large[0].getHeight();
    }
    public void resetPosition() {
        vehicleX = 0 - getLargeCarWidth();
        vehicleY = 1436;
        vehicleVelocity = 10; //+ random.nextInt(16);
    }
}
