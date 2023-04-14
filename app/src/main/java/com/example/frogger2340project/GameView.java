package com.example.frogger2340project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private static int deviceWidth;
    private static int deviceHeight;
    private String globalDifficulty;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<MediumCar> mediumCars;
    private ArrayList<LargeCar> largeCars;

    private ArrayList<Log> logs;
    private ArrayList<Log> logs2;
    private ArrayList<Log> logs3;
    private ArrayList<SmallLog> smallLogs;
    private ArrayList<SmallLog> smallLogs2;
    private ArrayList<SmallLog> smallLogs3;
    private ArrayList<SmallLog> smallLogs4;
    private ArrayList<SmallLog> smallLogs5;
    private ArrayList<MediumCar> mediumCars2;
    private float maxHeight = 50000.0f;
    private Frog newFrog;
    private MediumCar newMediumCar;
    private MediumCar newMediumCar2;
    private boolean onLog;

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
        logs = new ArrayList<>();
        logs2 = new ArrayList<>();
        logs3 = new ArrayList<>();
        smallLogs = new ArrayList<>();
        smallLogs2 = new ArrayList<>();
        smallLogs3 = new ArrayList<>();
        smallLogs4 = new ArrayList<>();
        smallLogs5 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = new Vehicle(context);
            vehicle.setVehicleX(200 * i);
            vehicles.add(vehicle);
            Log log = new Log(context);
            log.setLogX(200 * i);
            logs.add(log);
            Log log2 = new Log(context);
            log2.resetPosition2();
            log2.setLogX(400* i);
            logs2.add(log2);
            Log log3 = new Log(context);
            log3.resetPosition3();
            log3.setLogX(300 * i);
            logs3.add(log3);
            SmallLog smallLog = new SmallLog(context);
            smallLog.setLogX(600 * i);
            smallLogs.add(smallLog);
            SmallLog smallLog2 = new SmallLog(context);
            smallLog2.resetPosition2();
            smallLog2.setLogX(250 * i);
            smallLogs2.add(smallLog2);
            SmallLog smallLog3 = new SmallLog(context);
            smallLog3.resetPosition3();
            smallLog3.setLogX(500 * i);
            smallLogs3.add(smallLog3);
            SmallLog smallLog4 = new SmallLog(context);
            smallLog4.resetPosition4();
            smallLog4.setLogX(700 * i);
            smallLogs4.add(smallLog4);
            SmallLog smallLog5 = new SmallLog(context);
            smallLog5.resetPosition5();
            smallLog5.setLogX(450 * i);
            smallLogs5.add(smallLog5);
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
            canvas.drawBitmap(logs.get(i).getLog(logs.get(i).getLogFrame()),
                    logs.get(i).getLogX(), logs.get(i).getLogY(), null);
            canvas.drawBitmap(logs2.get(i).getLog(logs2.get(i).getLogFrame()),
                    logs2.get(i).getLogX(), logs2.get(i).getLogY(), null);
            canvas.drawBitmap(logs3.get(i).getLog(logs3.get(i).getLogFrame()),
                    logs3.get(i).getLogX(), logs3.get(i).getLogY(), null);
            canvas.drawBitmap(smallLogs.get(i).getLog(smallLogs.get(i).getLogFrame()),
                    smallLogs.get(i).getLogX(), smallLogs.get(i).getLogY(), null);
            canvas.drawBitmap(smallLogs2.get(i).getLog(smallLogs2.get(i).getLogFrame()),
                    smallLogs2.get(i).getLogX(), smallLogs2.get(i).getLogY(), null);
            canvas.drawBitmap(smallLogs3.get(i).getLog(smallLogs3.get(i).getLogFrame()),
                    smallLogs3.get(i).getLogX(), smallLogs3.get(i).getLogY(), null);
            canvas.drawBitmap(smallLogs4.get(i).getLog(smallLogs4.get(i).getLogFrame()),
                    smallLogs4.get(i).getLogX(), smallLogs4.get(i).getLogY(), null);
            canvas.drawBitmap(smallLogs5.get(i).getLog(smallLogs5.get(i).getLogFrame()),
                    smallLogs5.get(i).getLogX(), smallLogs5.get(i).getLogY(), null);
            mediumCars.get(i).setVehicleFrame(mediumCars.get(i).getVehicleFrame() + 1);
            vehicles.get(i).setVehicleFrame(vehicles.get(i).getVehicleFrame() + 1);
            largeCars.get(i).setVehicleFrame(largeCars.get(i).getVehicleFrame() + 1);
            logs.get(i).setLogFrame(logs.get(i).getLogFrame() + 1);
            logs2.get(i).setLogFrame(logs2.get(i).getLogFrame() + 1);
            logs3.get(i).setLogFrame(logs3.get(i).getLogFrame() + 1);
            smallLogs.get(i).setLogFrame(smallLogs.get(i).getLogFrame() + 1);
            smallLogs2.get(i).setLogFrame(smallLogs2.get(i).getLogFrame() + 1);
            smallLogs3.get(i).setLogFrame(smallLogs3.get(i).getLogFrame() + 1);
            smallLogs4.get(i).setLogFrame(smallLogs4.get(i).getLogFrame() + 1);
            smallLogs5.get(i).setLogFrame(smallLogs5.get(i).getLogFrame() + 1);
            if (vehicles.get(i).getVehicleFrame() > 2) {
                vehicles.get(i).setVehicleFrame(0);
            }
            if (mediumCars.get(i).getVehicleFrame() > 2) {
                mediumCars.get(i).setVehicleFrame(0);
            }
            if (largeCars.get(i).getVehicleFrame() > 2) {
                largeCars.get(i).setVehicleFrame(0);
            }
            if (logs.get(i).getLogFrame() > 2) {
                logs.get(i).setLogFrame(0);
            }
            if (logs2.get(i).getLogFrame() > 2) {
                logs2.get(i).setLogFrame(0);
            }
            if (logs3.get(i).getLogFrame() > 2) {
                logs3.get(i).setLogFrame(0);
            }
            if (smallLogs.get(i).getLogFrame() > 2) {
                smallLogs.get(i).setLogFrame(0);
            }
            if (smallLogs2.get(i).getLogFrame() > 2) {
                smallLogs2.get(i).setLogFrame(0);
            }
            if (smallLogs3.get(i).getLogFrame() > 2) {
                smallLogs3.get(i).setLogFrame(0);
            }
            if (smallLogs4.get(i).getLogFrame() > 2) {
                smallLogs4.get(i).setLogFrame(0);
            }
            if (smallLogs5.get(i).getLogFrame() > 2) {
                smallLogs5.get(i).setLogFrame(0);
            }
            vehicles.get(i).setVehicleX(vehicles.get(i).getVehicleX()
                    - vehicles.get(i).getVehicleVelocity());
            mediumCars.get(i).setVehicleX(mediumCars.get(i).getVehicleX()
                    - mediumCars.get(i).getVehicleVelocity());
            largeCars.get(i).setVehicleX(largeCars.get(i).getVehicleX()
                    + largeCars.get(i).getVehicleVelocity());
            logs.get(i).setLogX(logs.get(i).getLogX() - logs.get(i).getLogVelocity());
            logs2.get(i).setLogX(logs2.get(i).getLogX() - logs2.get(i).getLogVelocity());
            logs3.get(i).setLogX(logs3.get(i).getLogX() + logs3.get(i).getLogVelocity());
            smallLogs.get(i).setLogX(smallLogs.get(i).getLogX() - smallLogs.get(i).getLogVelocity());
            smallLogs2.get(i).setLogX(smallLogs2.get(i).getLogX() + smallLogs2.get(i).getLogVelocity());
            smallLogs3.get(i).setLogX(smallLogs3.get(i).getLogX() + smallLogs3.get(i).getLogVelocity());
            smallLogs4.get(i).setLogX(smallLogs4.get(i).getLogX() - smallLogs4.get(i).getLogVelocity());
            smallLogs5.get(i).setLogX(smallLogs5.get(i).getLogX() + smallLogs5.get(i).getLogVelocity());
            if (vehicles.get(i).getVehicleX() < -vehicles.get(i).getCarWidth()) {
                vehicles.get(i).resetPosition();
            }
            if (mediumCars.get(i).getVehicleX() < -mediumCars.get(i).getMediumCarWidth()) {
                mediumCars.get(i).resetPosition();
            }
            if (largeCars.get(i).getVehicleX() > deviceWidth) {
                largeCars.get(i).resetPosition();
            }
            if (logs.get(i).getLogX() < -logs.get(i).getLogWidth()) {
                logs.get(i).resetPosition();
            }
            if (logs2.get(i).getLogX() < -logs2.get(i).getLogWidth()) {
                logs2.get(i).resetPosition2();
            }
            if (logs3.get(i).getLogX() > deviceWidth) {
                logs3.get(i).resetPosition3();
            }
            if (smallLogs.get(i).getLogX() < -smallLogs.get(i).getLogWidth()) {
                smallLogs.get(i).resetPosition();
            }
            if (smallLogs2.get(i).getLogX() > deviceWidth) {
                smallLogs2.get(i).resetPosition2();
            }
            if (smallLogs3.get(i).getLogX() > deviceWidth) {
                smallLogs3.get(i).resetPosition3();
            }
            if (smallLogs4.get(i).getLogX() < -smallLogs4.get(i).getLogWidth()) {
                smallLogs4.get(i).resetPosition4();
            }
            if (smallLogs5.get(i).getLogX() > deviceWidth) {
                smallLogs5.get(i).resetPosition5();
            }
        }
        canvas.drawBitmap(frog, newFrog.getFrogX(), newFrog.getFrogY(), null);
        for (int i = 0; i < vehicles.size(); i++) {
            if (checkVehicleCollision(vehicles.get(i).getVehicleX(), vehicles.get(i).getVehicleY(),
                    vehicles.get(i).getCarWidth(), vehicles.get(i).getCarHeight(),
                    newFrog.getFrogX(), newFrog.getFrogY())) {
                updateLives();
            }
        }
        for (int i = 0; i < mediumCars.size(); i++) {
            if (checkVehicleCollision(mediumCars.get(i).getVehicleX(),
                    mediumCars.get(i).getVehicleY(), mediumCars.get(i).getMediumCarWidth(),
                    mediumCars.get(i).getMediumCarHeight(), newFrog.getFrogX(),
                    newFrog.getFrogY())) {
                updateLives();
            }
        }
        for (int i = 0; i < largeCars.size(); i++) {
            if (checkVehicleCollision(largeCars.get(i).getVehicleX(),
                    largeCars.get(i).getVehicleY(), largeCars.get(i).getLargeCarWidth(),
                    largeCars.get(i).getLargeCarHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                updateLives();
            }
        }
        onLog = false;
        for (int i = 0; i < logs.size(); i++) {
            if (checkLogCollision(logs.get(i).getLogX(), logs.get(i).getLogY(), logs.get(i).getLogWidth(),
                    logs.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogLeftLog(newFrog.getFrogX() - logs.get(i).getLogVelocity())) {
                    newFrog.moveFrogLeft(newFrog.getFrogX() - logs.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        for (int i = 0; i < logs2.size(); i++) {
            if (checkLogCollision(logs2.get(i).getLogX(), logs2.get(i).getLogY(), logs2.get(i).getLogWidth(),
                    logs2.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogLeftLog(newFrog.getFrogX() - logs2.get(i).getLogVelocity())) {
                    newFrog.moveFrogLeft(newFrog.getFrogX() - logs2.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        for (int i = 0; i < logs3.size(); i++) {
            if (checkLogCollision(logs3.get(i).getLogX(), logs3.get(i).getLogY(), logs3.get(i).getLogWidth(),
                    logs3.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogRightLog(newFrog.getFrogX() + logs3.get(i).getLogVelocity())) {
                    newFrog.moveFrogRight(newFrog.getFrogX() + logs3.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        for (int i = 0; i < smallLogs.size(); i++) {
            if (checkLogCollision(smallLogs.get(i).getLogX(), smallLogs.get(i).getLogY(), smallLogs.get(i).getLogWidth(),
                    smallLogs.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogLeftLog(newFrog.getFrogX() - smallLogs.get(i).getLogVelocity())) {
                    newFrog.moveFrogLeft(newFrog.getFrogX() - smallLogs.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        for (int i = 0; i < smallLogs2.size(); i++) {
            if (checkLogCollision(smallLogs2.get(i).getLogX(), smallLogs2.get(i).getLogY(), smallLogs2.get(i).getLogWidth(),
                    smallLogs2.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogRightLog(newFrog.getFrogX() + smallLogs2.get(i).getLogVelocity())) {
                    newFrog.moveFrogRight(newFrog.getFrogX() + smallLogs2.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        for (int i = 0; i < smallLogs3.size(); i++) {
            if (checkLogCollision(smallLogs3.get(i).getLogX(), smallLogs3.get(i).getLogY(), smallLogs3.get(i).getLogWidth(),
                    smallLogs3.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogRightLog(newFrog.getFrogX() + smallLogs3.get(i).getLogVelocity())) {
                    newFrog.moveFrogRight(newFrog.getFrogX() + smallLogs3.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
            if (checkLogCollision(smallLogs4.get(i).getLogX(), smallLogs4.get(i).getLogY(), smallLogs4.get(i).getLogWidth(),
                    smallLogs4.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogLeftLog(newFrog.getFrogX() - smallLogs4.get(i).getLogVelocity())) {
                    newFrog.moveFrogLeft(newFrog.getFrogX() - smallLogs4.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
            if (checkLogCollision(smallLogs5.get(i).getLogX(), smallLogs5.get(i).getLogY(), smallLogs5.get(i).getLogWidth(),
                    smallLogs5.get(i).getLogHeight(), newFrog.getFrogX(), newFrog.getFrogY())) {
                onLog = true;
                if (newFrog.moveFrogRightLog(newFrog.getFrogX() + smallLogs5.get(i).getLogVelocity())) {
                    newFrog.moveFrogRight(newFrog.getFrogX() + smallLogs5.get(i).getLogVelocity());
                } else {
                    updateLives();
                }
                break;
            }
        }
        if (!onLog && checkCollision(newFrog.getFrogY())) {
            updateLives();
        }
        if (checkVehicleCollision(newMediumCar.getVehicleX(), newMediumCar.getVehicleY(),
                mediumCars.get(0).getMediumCarWidth(), mediumCars.get(0).getMediumCarHeight(),
                newFrog.getFrogX(), newFrog.getFrogY())) {
            updateLives();
        }
        if (checkVehicleCollision(newMediumCar2.getVehicleX(), newMediumCar2.getVehicleY(),
                mediumCars.get(0).getMediumCarWidth(), mediumCars.get(0).getMediumCarHeight(),
                newFrog.getFrogX(), newFrog.getFrogY())) {
            updateLives();
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

    public static boolean checkVehicleCollision(int vehicleX, int vehicleY,
                                                int vehicleWidth, int vehicleHeight,
                                         float frogX, float frogY) {
        return vehicleX + vehicleWidth >= frogX && vehicleX <= frogX + 78
                && vehicleY + vehicleHeight >= frogY && vehicleY <= frogY + 82;
    }

    public static boolean checkLogCollision(int logX, int logY, int logWidth, int logHeight,
                                            float frogX, float frogY) {
        return logX + logWidth >= frogX && logX <= frogX + 78 && logY + logHeight >= frogY && logY <= frogY + 82;
    }

    public static boolean checkCollision(float frogY) {
        return frogY <= 1044 && frogY >= 358;
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
        if (newFrog.getFrogY() == 260) {
            winScreen();
        }
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
            Intent intent = new Intent(context, GameOver.class);
            intent.putExtra("score", newFrog.getScore());
            context.startActivity(intent);
            ((Activity) context).finish();
        } else {
            newFrog.setScore(0);
            newFrog.setMaxHeight();
            newFrog.setFrogX(deviceWidth / 2 - frog.getWidth() / 2);
            newFrog.setFrogY(deviceHeight - 206 - frog.getHeight() / 2);
        }
    }

    public void winScreen() {
        Intent intent = new Intent(context, GameWin.class);
        intent.putExtra("score", newFrog.getScore());
        context.startActivity(intent);
        ((Activity) context).finish();
    }


    public static boolean checkWin(float frogY) {
        return frogY == 260;
    }
}
