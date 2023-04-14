package com.example.frogger2340project;

public class Frog {
    private float frogX;
    private float frogY;

    private int score;

    private int scoreHeight;

    private int scoreMult = 1;

    private float maxHeight = 50000.0f;

    public Frog(float x, float y, int score) {
        this.frogX = x;
        this.frogY = y;
        this.score = score;
    }

    public Frog() {
        this.frogX = 0;
        this.frogY = 0;
        this.score = 0;
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
        if (newFrogY < maxHeight) {
            maxHeight = newFrogY;
            score += scoreMult++ * 5;
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

    public boolean moveFrogLeftLog(float newFrogX) {
        return !(newFrogX < 33);
    }

    public boolean moveFrogRightLog(float newFrogX) {
        return !(newFrogX > 969);
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

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setMaxHeight() {
        this.maxHeight = 5000.0f;
    }
}
