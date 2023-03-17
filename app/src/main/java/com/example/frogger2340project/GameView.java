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
    private Bitmap mediumCar2;
    private Bitmap mediumCar2Back;
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
    private ArrayList<MediumCar> mediumCars2;
    private float maxHeight = 50000.0f;
    private Frog newFrog;
    private MediumCar newMediumCar;
    private MediumCar newMediumCar2;

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
        lives = livesCount(globalDifficulty);
        frog = Bitmap.createScaledBitmap(frog, frog.getWidth() * 2,
                frog.getHeight() * 2, false);
        mediumCar2 = BitmapFactory.decodeResource(getResources(), R.drawable.mediumcar);
        mediumCar2 = Bitmap.createScaledBitmap(mediumCar2, mediumCar2.getWidth() * 2,
                mediumCar2.getHeight() * 2, false);
        mediumCar2Back = BitmapFactory.decodeResource(getResources(), R.drawable.mediumcar);
        mediumCar2Back = Bitmap.createScaledBitmap(mediumCar2Back,
                mediumCar2Back.getWidth() * 2, mediumCar2Back.getHeight() * 2,
                false);
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
        newFrog = new Frog(deviceWidth / 2 - frog.getWidth() / 2,
                deviceHeight - 206 - frog.getHeight() / 2, 0);
        newMediumCar = new MediumCar(GameView.getDeviceWidth() + mediumCar2.getWidth(),
                1338, 5);
        newMediumCar2 = new MediumCar(0 - mediumCar2Back.getWidth(), 1240, 1);
        vehicles = new ArrayList<>();
        mediumCars = new ArrayList<>();
        largeCars = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle(context);
            vehicle.setVehicleX(200 * i);
            vehicles.add(vehicle);
            MediumCar medium = new MediumCar(context);
            medium.setVehicleX(300 * i);
            mediumCars.add(medium);
            LargeCar large = new LargeCar(context);
            large.setVehicleX(400 * i);
            largeCars.add(large);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(frog, newFrog.getFrogX(), newFrog.getFrogY(), null);
        canvas.drawBitmap(mediumCar2, newMediumCar.getVehicleX(),
                newMediumCar.getVehicleY(), null);
        canvas.drawBitmap(mediumCar2Back, newMediumCar2.getVehicleX(),
                newMediumCar2.getVehicleY(), null);
        newMediumCar2.setVehicleX(newMediumCar2.getVehicleX() + newMediumCar2.getVehicleVelocity());
        newMediumCar2.accelerate();
        if (checkOutOfBoundsMoveRight(newMediumCar2.getVehicleX(), deviceWidth)) {
            newMediumCar2.resetPosition3(mediumCar2Back.getWidth());
        }
        newMediumCar.setVehicleX(newMediumCar.getVehicleX() - newMediumCar.getVehicleVelocity());
        if (checkOutOfBoundsMoveLeft(newMediumCar.getVehicleX(), 0 - mediumCar2.getWidth())) {
            newMediumCar.resetPosition2(mediumCar2.getWidth());
        }
        for (int i = 0; i < vehicles.size(); i++) {
            canvas.drawBitmap(vehicles.get(i).getCar(vehicles.get(i).getVehicleFrame()),
                    vehicles.get(i).getVehicleX(), vehicles.get(i).getVehicleY(), null);
            canvas.drawBitmap(mediumCars.get(i).getMediumCar(mediumCars.get(i).getVehicleFrame()),
                    mediumCars.get(i).getVehicleX(), mediumCars.get(i).getVehicleY(), null);
            canvas.drawBitmap(largeCars.get(i).getLargeCar(largeCars.get(i).getVehicleFrame()),
                    largeCars.get(i).getVehicleX(), largeCars.get(i).getVehicleY(), null);
            mediumCars.get(i).setVehicleFrame(mediumCars.get(i).getVehicleFrame() + 1);
            vehicles.get(i).setVehicleFrame(vehicles.get(i).getVehicleFrame() + 1);
            largeCars.get(i).setVehicleFrame(largeCars.get(i).getVehicleFrame() + 1);
            if (vehicles.get(i).getVehicleFrame() > 2) {
                vehicles.get(i).setVehicleFrame(0);
            }
            if (mediumCars.get(i).getVehicleFrame() > 2) {
                mediumCars.get(i).setVehicleFrame(0);
            }
            if (largeCars.get(i).getVehicleFrame() > 2) {
                largeCars.get(i).setVehicleFrame(0);
            }
            vehicles.get(i).setVehicleX(vehicles.get(i).getVehicleX()
                    - vehicles.get(i).getVehicleVelocity());
            mediumCars.get(i).setVehicleX(mediumCars.get(i).getVehicleX()
                    - mediumCars.get(i).getVehicleVelocity());
            largeCars.get(i).setVehicleX(largeCars.get(i).getVehicleX()
                    + largeCars.get(i).getVehicleVelocity());
            if (vehicles.get(i).getVehicleX() < -vehicles.get(i).getCarWidth()) {
                vehicles.get(i).resetPosition();
            }
            if (mediumCars.get(i).getVehicleX() < -mediumCars.get(i).getMediumCarWidth()) {
                mediumCars.get(i).resetPosition();
            }
            if (largeCars.get(i).getVehicleX() > deviceWidth) {
                largeCars.get(i).resetPosition();
            }
        }

        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getVehicleX() + vehicles.get(i).getCarWidth() >= newFrog.getFrogX()
                    && vehicles.get(i).getVehicleX() <= newFrog.getFrogX() //+ frog.getWidth()
                    && vehicles.get(i).getVehicleY() + vehicles.get(i).getCarHeight()
                    >= newFrog.getFrogY()
                    && vehicles.get(i).getVehicleY() <= newFrog.getFrogY()) { //+ frog.getHeight()
                lives--;
            }
        }
        canvas.drawText(globalDifficulty, 20, textSize, textPaint);
        canvas.drawText("Lives: " + lives, deviceWidth / 2, textSize, textPaint);
        canvas.drawText("Score: " + newFrog.getScore(), deviceWidth / 2, textSize * 2, textPaint);
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
        checkCollision();
        return true;
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

    public static boolean checkOutOfBoundsMoveRight(int current, int bounds) {
        return current > bounds;
    }

    public static boolean checkOutOfBoundsMoveLeft(int current, int bounds) {
        return current < bounds;
    }

    public void updateLives() {
        lives--;
        if (lives == 0) {
            //GAME OVER SCREEN IMPLEMENTATION
        } else {
            newFrog.setScore(0);
            maxHeight = 50000.0f;
            newFrog.setFrogX(deviceWidth / 2 - frog.getWidth() / 2);
            newFrog.setFrogY(deviceHeight - 206 - frog.getHeight() / 2);
        }
    }

    public void checkCollision() {
        if (newFrog.getFrogY() <= 1044 && newFrog.getFrogY() >= 358) {
            updateLives();
        }
    }
}
