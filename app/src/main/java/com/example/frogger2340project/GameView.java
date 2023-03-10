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
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

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
    private static int score;
    private float oldFrogX;
    private float oldX;
    private static int deviceWidth;
    private static int deviceHeight;
    private String globalDifficulty;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<MediumCar> mediumCars;
    private ArrayList<LargeCar> largeCars;
    private float maxHeight = 50000.0f;
    private Frog newFrog;

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
        newFrog = new Frog(deviceWidth / 2 - frog.getWidth() / 2, deviceHeight - 206 - frog.getHeight() / 2);
        vehicles = new ArrayList<>();
        mediumCars = new ArrayList<>();
        largeCars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle(context);
            vehicle.vehicleX = 200*i;
            vehicles.add(vehicle);
            MediumCar medium = new MediumCar(context);
            medium.vehicleX = 300*i;
            mediumCars.add(medium);
            LargeCar large = new LargeCar(context);
            large.vehicleX = -(400*i);
            largeCars.add(large);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(frog, newFrog.getFrogX(), newFrog.getFrogY(), null);
        for (int i = 0; i < vehicles.size(); i++) {
            canvas.drawBitmap(vehicles.get(i).getCar(vehicles.get(i).vehicleFrame), vehicles.get(i).vehicleX, vehicles.get(i).vehicleY, null);
            canvas.drawBitmap(mediumCars.get(i).getMediumCar(mediumCars.get(i).vehicleFrame), mediumCars.get(i).vehicleX, mediumCars.get(i).vehicleY, null);
            canvas.drawBitmap(largeCars.get(i).getLargeCar(largeCars.get(i).vehicleFrame), largeCars.get(i).vehicleX, largeCars.get(i).vehicleY, null);
            mediumCars.get(i).vehicleFrame++;
            vehicles.get(i).vehicleFrame++;
            largeCars.get(i).vehicleFrame++;
            if (vehicles.get(i).vehicleFrame > 2) {
                vehicles.get(i).vehicleFrame = 0;
            }
            if (mediumCars.get(i).vehicleFrame > 2) {
                mediumCars.get(i).vehicleFrame = 0;
            }
            if (largeCars.get(i).vehicleFrame > 2) {
                largeCars.get(i).vehicleFrame = 0;
            }
            vehicles.get(i).vehicleX -= vehicles.get(i).vehicleVelocity;
            mediumCars.get(i).vehicleX -= mediumCars.get(i).vehicleVelocity;
            largeCars.get(i).vehicleX += largeCars.get(i).vehicleVelocity;
            if (vehicles.get(i).vehicleX < 0) {
                vehicles.get(i).resetPosition();
            }
            if (mediumCars.get(i).vehicleX < 0) {
                mediumCars.get(i).resetPosition();
            }
            if (largeCars.get(i).vehicleX > deviceWidth) {
                largeCars.get(i).resetPosition();
            }
        }

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).vehicleX + vehicles.get(i).getCarWidth() >= newFrog.getFrogX()
            && vehicles.get(i).vehicleX <= newFrog.getFrogX() //+ frog.getWidth()
            && vehicles.get(i).vehicleY + vehicles.get(i).getCarHeight() >= newFrog.getFrogY()
            && vehicles.get(i).vehicleY <= newFrog.getFrogY()) { //+ frog.getHeight() ) {
                lives--;
            }
        }
        lives = livesCount(globalDifficulty);
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
                float newFrogY = newFrog.getFrogY() - frog.getHeight() - 16;
                newFrog.moveFrogUp(newFrogY);
                if (newFrogY < maxHeight) {
                    maxHeight = newFrogY;
                    incrementScore(newFrogY);
                }
            }
        } else if (touchY > newFrog.getFrogY() + frog.getHeight()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogY = newFrog.getFrogY() + frog.getHeight() + 16;
                newFrog.moveFrogDown(newFrogY);
            }
        } else if (touchX > newFrog.getFrogX() + frog.getWidth()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogX = newFrog.getFrogX() + frog.getWidth();
                newFrog.moveFrogRight(newFrogX);
            }
        } else if (touchX < newFrog.getFrogX()) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                float newFrogX = newFrog.getFrogX() - frog.getWidth();
                newFrog.moveFrogLeft(newFrogX);
            }
        }
        return true;
    }

    public static void incrementScore(float frogY) {
        int intVer = (int) frogY;
        switch (intVer) {
            case 1632: score += 10;
                       break;
            case 1534: score += 20;
                       break;
            case 1436: score += 30;
                       break;
            case 1338: score += 40;
                       break;
            case 1240: score += 50;
                       break;
            default: score += 5;

        }
    }

    public static int getDeviceWidth() {
        return deviceWidth;
    }
    public static int livesCount(String difficulty) {
        if (difficulty.equals("Easy")) {
            return 3;
        } else if (difficulty.equals("Medium")) {
            return 2;
        } else {
            return 1;
        }
    }
}
