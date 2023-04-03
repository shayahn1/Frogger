package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Sprint4Test {
    private Frog frog1;
    private MediumCar mediumCar;
    private MediumCar mediumCar2;

    @Before
    public void init() {
        frog1 = new Frog();
        mediumCar = new MediumCar(50, 50, 5);
        mediumCar2 = new MediumCar(500, 500, 0);
    }

    @Test
    public void testVehicleCollisionMiss() {
        assertEquals(true, GameView.checkVehicleCollision(mediumCar.getVehicleX(), mediumCar.getVehicleY(),
                5, 5, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionBaseCase() {
        mediumCar.setVehicleX(0);
        mediumCar.setVehicleY(0);
        assertEquals(true, GameView.checkVehicleCollision(mediumCar.getVehicleX(), mediumCar.getVehicleY(),
                5, 5, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionUp() {
        assertEquals(true, GameView.checkVehicleCollision(mediumCar.getVehicleX(), mediumCar.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.setFrogX(50);
        mediumCar.setVehicleY(260);
        frog1.moveFrogUp(260);
        assertEquals(true, GameView.checkVehicleCollision(mediumCar.getVehicleX(), mediumCar.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionDown() {
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(500);
        frog1.moveFrogDown(300);
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogDown(501);
        assertEquals(true, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionRight() {
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogDown(500);
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(300);
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(502);
        assertEquals(true, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionLeft() {
        frog1.moveFrogDown(500);
        frog1.setFrogX(1000);
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogLeft(600);
        assertEquals(false, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogLeft(508);
        assertEquals(true, GameView.checkVehicleCollision(mediumCar2.getVehicleX(), mediumCar2.getVehicleY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testRiverCollisionMiss() {
        assertEquals(false, GameView.checkCollision(frog1.getFrogY()));
    }

    @Test
    public void testRiverCollisionBottom() {
        frog1.moveFrogUp(1042);
        assertEquals(true, GameView.checkCollision(frog1.getFrogY()));
    }

    @Test
    public void testCrossRiverCollision() {
        frog1.moveFrogUp(500);
        assertEquals(true, GameView.checkCollision(frog1.getFrogY()));
        frog1.moveFrogUp(200);
        assertEquals(false, GameView.checkCollision(frog1.getFrogY()));
    }

    @Test
    public void testNewHighScore() {
        assertEquals(false, GameOver.isHighest(0, 50));
    }

    @Test
    public void testHighScoreFalse() {
        assertEquals(true, GameOver.isHighest(500, 50));
    }
}
