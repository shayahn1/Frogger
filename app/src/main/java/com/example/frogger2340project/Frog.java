package com.example.frogger2340project;

public class Frog {
    private float frogX;
    private float frogY;

    private int score;

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
            incrementScore(newFrogY);
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

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(float frogY) {
        int intVer = (int) frogY;
        switch (intVer) {
        case 1632:
            score += 5;
            break;
        case 1534:
            score += 10;
            break;
        case 1436:
            score += 15;
            break;
        case 1338:
            score += 20;
            break;
        case 1240:
            score += 25;
            break;
        case 1142:
            score += 30;
            break;
        case 1044:
            score += 35;
            break;
        case 946:
            score += 40;
            break;
        case 848:
            score += 45;
            break;
        case 750:
            score += 50;
            break;
        case 652:
            score += 55;
            break;
        case 554:
            score += 60;
            break;
        case 456:
            score += 65;
            break;
        case 358:
            score += 70;
            break;
        case 260:
            score += 75;
            break;
        default:
            break;
        }
    }
}
