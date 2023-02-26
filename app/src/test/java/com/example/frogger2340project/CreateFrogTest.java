package com.example.frogger2340project;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
        frog2.moveFrogUp(200);
        assertEquals(frog2.getFrogY(), 260, 0);
    }
    @Test
    public void testMoveFrogDown() {
        frog1.moveFrogDown(800);
        assertEquals(frog1.getFrogY(), 800, 0);
        frog1.moveFrogDown(1800);
        assertEquals(frog1.getFrogY(), 1736, 0);
    }

    @Test
    public void testMoveFrogLeft() {
        frog2.moveFrogLeft(100);
        assertEquals(frog2.getFrogX(), 100, 0);
        frog2.moveFrogLeft(-100);
        assertEquals(frog2.getFrogX(), 33, 0);
    }

    @Test
    public void testMoveFrogRight() {
        frog1.moveFrogRight(600);
        assertEquals(frog1.getFrogX(), 600, 0);
        frog1.moveFrogRight(2000);
        assertEquals(frog1.getFrogX(), 969, 0);
    }
}
