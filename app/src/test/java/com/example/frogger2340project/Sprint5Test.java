package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Sprint5Test {
    private Frog frog1;
    private Log log;
    private Log log2;

    @Before
    public void init() {
        frog1 = new Frog();
        log = new Log(50, 50, 5);
        log2 = new Log(500, 500, 0);
    }

    @Test
    public void testLogCreation() {
        assertEquals(50, log.getLogX());
        assertEquals(50, log.getLogY());
        assertEquals(5, log.getLogVelocity());
    }

    @Test
    public void testLogCollision() {
        assertEquals(true, GameView.checkLogCollision(log.getLogX(), log.getLogY(),
                5, 5, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testLogCollisionBaseCase() {
        log.setLogX(0);
        log.setLogY(0);
        assertEquals(true, GameView.checkLogCollision(log.getLogX(), log.getLogY(),
                5, 5, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void getLogCollisionUp() {
        assertEquals(true, GameView.checkLogCollision(log.getLogX(), log.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.setFrogX(50);
        log.setLogY(260);
        frog1.moveFrogUp(260);
        assertEquals(true, GameView.checkLogCollision(log.getLogX(), log.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testLogCollisionDown() {
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(500);
        frog1.moveFrogDown(300);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogDown(501);
        assertEquals(true, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testLogCollisionRight() {
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogDown(500);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(300);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogRight(502);
        assertEquals(true, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testVehicleCollisionLeft() {
        frog1.moveFrogDown(500);
        frog1.setFrogX(1000);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogLeft(600);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        frog1.moveFrogLeft(508);
        assertEquals(true, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
    }

    @Test
    public void testRiverCollisionMiss() {
        assertEquals(false, GameView.checkCollision(frog1.getFrogY()));
    }

    @Test
    public void testRiverCollisionOnLog() {
        assertEquals(false, GameView.checkCollision(frog1.getFrogY()));
        frog1.moveFrogUp(501);
        frog1.moveFrogRight(500);
        assertEquals(true, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        if (!GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY())) {
            assertEquals(true, GameView.checkCollision(frog1.getFrogY()));
        }
    }

    @Test
    public void testRiverCollisionMissesLog() {
        assertEquals(false, GameView.checkCollision(frog1.getFrogY()));
        frog1.moveFrogUp(600);
        frog1.moveFrogRight(500);
        assertEquals(false, GameView.checkLogCollision(log2.getLogX(), log2.getLogY(),
                10, 10, frog1.getFrogX(), frog1.getFrogY()));
        assertEquals(true, GameView.checkCollision(frog1.getFrogY()));
    }

    @Test
    public void checkWinScreen() {
        assertEquals(true, GameView.checkWinScreen(260));
    }

}
