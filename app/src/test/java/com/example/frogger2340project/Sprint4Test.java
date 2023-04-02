package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class Sprint4Test {
    private Frog frog1;
    private Bitmap frog2;
    private MediumCar mediumCar;
    private GameView game;

    @Test
    public void testWaterTileLives() {
        frog1 = new Frog(500, 800, 10);
        int temp = game.getLives();

        game.setFrog(frog1);
        game.checkCollision();
        assertEquals(temp, game.getLives() + 1);

    }

    @Test
    public void testWaterTileResetPositionX() {
        frog1 = new Frog(500, 800, 10);
        float xPos = frog1.getFrogX();
        game.checkCollision();
        assertEquals(frog1.getFrogX(), game.getDeviceWidth() / 2 - game.getFrogWidth() / 2);
    }

    @Test
    public void testWaterTileResetPositionY() {
        frog1 = new Frog(500, 800, 10);
        float yPos = frog1.getFrogY();
        game.checkCollision();
        assertEquals(frog1.getFrogX(), game.getDeviceHeight() - 206 - game.getFrogHeight() / 2);
    }

    @Test
    public void testWaterTileGameOver() {
        game.setLives(1);
        frog1 = new Frog(500, 800, 10);
        game.checkCollision();
        assertEquals(game.getLives(), 0);
        //don't know how to check that it's on the game over screen
    }

    @Test
    public void testWaterTileScoreReset() {
        frog1 = new Frog(500, 800, 10);
        game.checkCollision();
        assertEquals(frog1.getScore(), 0);
    }

    @Test
    public void testVehicleCollisionPositionY() {
        mediumCar = new MediumCar(50, 50, 5);
        frog1 = new Frog(51, 51, 10);
        game.setLives(3);
        game.checkCollision();
        assertEquals(frog1.getFrogY(), game.getDeviceHeight() - 206 - game.getFrogHeight() / 2);
    }

    @Test
    public void testVehicleCollisionPositionX() {
        mediumCar = new MediumCar(50, 50, 5);
        frog1 = new Frog(51, 51, 10);
        game.setLives(3);
        game.checkCollision();
        assertEquals(frog1.getFrogX(), game.getDeviceWidth() / 2 - game.getFrogWidth() / 2);
    }

    @Test
    public void testVehicleCollisionLives() {
        mediumCar = new MediumCar(50, 50, 5);
        frog1 = new Frog(51, 51, 10);
        game.setLives(4);
        game.checkCollision();
        assertEquals(game.getLives(), 3);
    }

    @Test
    public void testVehicleCollisionScoreReset() {
        mediumCar = new MediumCar(50, 50, 5);
        frog1 = new Frog(51, 51, 10);
        game.checkCollision();
        assertEquals(frog1.getScore(), 0);
    }

    @Test
    public void testVehicleCollisionGameOver() {
        mediumCar = new MediumCar(50, 50, 5);
        frog1 = new Frog(51, 51, 10);
        game.setLives(1);
        game.checkCollision();
        assertEquals(game.getLives(), 0);
        //need to figure out how to check for game over screen
    }

}
