package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

public class Sprint3Test {
    private Frog frog1;
    private Frog frog2;
    private MediumCar mediumCar;

    @Before
    public void init() {
        frog1 = new Frog();
        frog2 = new Frog(500, 1730, 10);
        mediumCar = new MediumCar(50, 50, 5);
    }

    //Shayahn Mirfendereski
    @Test
    public void testInitialScore() {
        assertEquals(frog1.getScore(), 0, 0);
        assertEquals(frog2.getScore(), 10, 0);
    }

    @Test
    public void testIncrementScore() {
        frog2.moveFrogUp(1632);
        frog1.moveFrogUp(1240);
        assertEquals(frog2.getScore(), 15, 0);
        assertEquals(frog1.getScore(), 25, 0);
    }

    @Test
    public void testNotIncrementScore() {
        frog2.moveFrogRight(800);
        assertEquals(10, frog2.getScore(), 0);
        frog2.moveFrogLeft(300);
        assertEquals(10, frog2.getScore(), 0);
        frog2.moveFrogDown(1000);
        assertEquals(10, frog2.getScore(), 0);
    }

    @Test
    public void testScoreAlreadyCrossed() {
        frog1.moveFrogUp(1632);
        assertEquals(5, frog1.getScore(), 0);
        frog1.moveFrogDown(1730);
        frog1.moveFrogUp(1632);
        assertEquals(5, frog1.getScore(), 0);
    }

    @Test
    public void testCreateMediumCar() {
        mediumCar = new MediumCar(50, 60, 100);
        assertEquals(50, mediumCar.getVehicleX(), 0);
        assertEquals(60, mediumCar.getVehicleY(), 0);
        assertEquals(100, mediumCar.getVehicleVelocity(), 0);
    }
    @Test
    public void testVehicleVelocity() {
        assertEquals(5, mediumCar.getVehicleVelocity(), 0);
        mediumCar.resetPosition2(50);
        assertEquals(10, mediumCar.getVehicleVelocity(), 0);
        mediumCar.resetPosition2(50);
        assertEquals(15, mediumCar.getVehicleVelocity(), 0);
    }

    @Test
    public void testAcceleration() {
        assertEquals(5, mediumCar.getVehicleVelocity(), 0);
        for(int i = 0; i < 5; i++) {
            mediumCar.accelerate();
        }
        assertEquals(10, mediumCar.getVehicleVelocity(), 0);
    }

    @Test
    public void checkBoundsRight() {
        assertEquals(false, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 500));
        assertEquals(true, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 10));
        mediumCar.setVehicleX(0);
        assertEquals(false, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 10));
    }

    @Test
    public void checkBoundsLeft() {
        assertEquals(false, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 0));
        assertEquals(true, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 100));
        mediumCar.setVehicleX(200);
        assertEquals(false, GameView.checkOutOfBoundsMoveRight(mediumCar.getVehicleX(), 100));
    }

    @Test
    public void checkFrogGettersAndSetters() {
        frog1.setFrogX(200);
        frog2.setFrogY(300);
        assertEquals(200, frog1.getFrogX(), 0);
        assertEquals(300, frog2.getFrogY(), 0);

        frog1.setScore(350);
        assertEquals(350, frog1.getScore());
    }

}
