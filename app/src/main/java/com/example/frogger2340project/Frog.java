package com.example.frogger2340project;

public class Frog {
    private float frogX;
    private float frogY;

    public Frog(float x, float y) {
        this.frogX = x;
        this.frogY = y;
    }

    public Frog() {
        this.frogX = 0;
        this.frogY = 0;
    }

    public float getFrogX() {
        return frogX;
    }

    public float getFrogY() {
        return frogY;
    }

    public void moveFrogUp(float newFrogY) {
        if (newFrogY < 260) {
            this.setFrogY(260);
        } else {
            this.setFrogY(newFrogY);
        }
    }

    public void moveFrogDown(float newFrogY) {
        if (newFrogY > 1736) {
            this.setFrogY(1736);
        } else {
            this.setFrogY(newFrogY);
        }
    }

    public void moveFrogRight(float newFrogX) {
        if (newFrogX > 969) {
            this.setFrogX(969);
        } else {
            this.setFrogX(newFrogX);
        }
    }

    public void moveFrogLeft(float newFrogX) {
        if (newFrogX < 33) {
            this.setFrogX(33);
        } else {
            this.setFrogX(newFrogX);
        }
    }

    public void setFrogX(float x) {
        this.frogX = x;
    }

    public void setFrogY(float y) {
        this.frogY = y;
    }

    public void setCoords(float x, float y) {
        this.frogX = x;
        this.frogY = y;
    }
}
