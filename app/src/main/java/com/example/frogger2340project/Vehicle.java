package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Vehicle {
    Bitmap vehicle[] = new Bitmap[3];
    int vehicleFrame = 0;
    int vehicleX, vehicleY, vehicleVelocity;

    public Vehicle(Context context) {
        vehicle[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        vehicle[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        vehicle[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        resetPosition();
    }

    public Bitmap getCar(int carFrame) {
        return vehicle[carFrame];
    }

    public int getCarWidth() {
        return vehicle[0].getWidth();
    }

    public int getCarHeight() {
        return vehicle[0].getHeight();
    }
    public void resetPosition() {
        vehicleX = GameView.getDeviceWidth() + getCarWidth();
        vehicleY = 1632;
        vehicleVelocity = 10; //+ random.nextInt(16);
    }
}
