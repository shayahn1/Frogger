package com.example.frogger2340project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class CreateFrogTest {
    private Frog frog1;
    private Frog frog2;

    @Before
    public void init() {
        frog1 = new Frog();
        frog2 = new Frog(500, 400);
    }
    @Test
    public void testCreateFrog() {
        assertEquals(frog1.getFrogX(), 0, 0);
        assertEquals(frog1.getFrogY(), 0, 0);
        assertEquals(frog2.getFrogX(), 500, 0);
        assertEquals(frog2.getFrogY(), 400, 0);
    }
    @Test
    public void testMoveFrogUp() {
        frog2.moveFrogUp(300);
        assertEquals(frog2.getFrogY(), 300, 0);
        frog2.moveFrogUp(1500);
        assertEquals(frog2.getFrogY(), 1500, 0);
    }
    @Test
    public void testMoveFrogDown() {
        frog1.moveFrogDown(800);
        assertEquals(frog1.getFrogY(), 800, 0);
        frog1.moveFrogDown(300);
        assertEquals(frog1.getFrogY(), 300, 0);
    }

    @Test
    public void testMoveFrogLeft() {
        frog2.moveFrogLeft(100);
        assertEquals(frog2.getFrogX(), 100, 0);
        frog2.moveFrogLeft(500);
        assertEquals(frog2.getFrogX(), 500, 0);
    }

    @Test
    public void testMoveFrogRight() {
        frog1.moveFrogRight(600);
        assertEquals(frog1.getFrogX(), 600, 0);
        frog1.moveFrogRight(100);
        assertEquals(frog1.getFrogX(), 100, 0);
    }

    @Test
    public void testFrogBoundaryUp() {
        frog2.moveFrogUp(0);
        assertNotEquals(frog2.getFrogY(), 0, 0);
        assertEquals(frog2.getFrogY(), 260, 0);
    }

    @Test
    public void testFrogBoundaryDown() {
        frog1.moveFrogDown(2000);
        assertNotEquals(frog1.getFrogY(), 2000, 0);
        assertEquals(frog1.getFrogY(), 1736, 0);
    }

    @Test
    public void testFrogBoundaryLeft() {
        frog2.moveFrogLeft(0);
        assertNotEquals(frog2.getFrogX(), 0, 0);
        assertEquals(frog2.getFrogX(), 33, 0);
    }

    @Test
    public void testFrogBoundaryRight() {
        frog1.moveFrogRight(1000);
        assertNotEquals(frog1.getFrogX(), 1000, 0);
        assertEquals(frog1.getFrogX(), 969, 0);
    }
}
