package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Vehicle {
    private Bitmap[] vehicle = new Bitmap[3];
    private int vehicleFrame = 0;
    private int vehicleX;
    private int vehicleY;
    private int vehicleVelocity;

    public Vehicle(Context context) {
        vehicle[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        vehicle[0] = Bitmap.createScaledBitmap(vehicle[0], vehicle[0].getWidth() * 2,
                vehicle[0].getHeight() * 2, false);
        vehicle[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        vehicle[1] = Bitmap.createScaledBitmap(vehicle[1], vehicle[1].getWidth() * 2,
                vehicle[1].getHeight() * 2, false);
        vehicle[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.smallcar);
        vehicle[2] = Bitmap.createScaledBitmap(vehicle[2], vehicle[2].getWidth() * 2,
                vehicle[2].getHeight() * 2, false);
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


    public Bitmap[] getVehicle() {
        return vehicle;
    }
    public int getVehicleFrame() {
        return vehicleFrame;
    }
    public int getVehicleX() {
        return vehicleX;
    }
    public int getVehicleY() {
        return vehicleY;
    }
    public int getVehicleVelocity() {
        return vehicleVelocity;
    }
    public void setVehicle(Bitmap[] newVehicle) {
        vehicle = newVehicle;
    }
    public void setVehicleFrame(int newVehicleFrame) {
        vehicleFrame = newVehicleFrame;
    }
    public void setVehicleX(int newVehicleX) {
        vehicleX = newVehicleX;
    }
    public void setVehicleY(int newVehicleY) {
        vehicleY = newVehicleY;
    }
    public void setVehicleVelocity(int newVehicleVelocity) {
        vehicleVelocity = newVehicleVelocity;
    }
}
