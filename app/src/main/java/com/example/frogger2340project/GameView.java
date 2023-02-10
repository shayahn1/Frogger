package com.example.frogger2340project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Handler;
import android.view.View;
public class GameView extends View {

    Bitmap background, frog;
    Rect rectBackground;
    Context context;
    Handler handler;
    final long UPDATE_MILLIS = 30;
    int lives;
    int points;
    float frogX, frogY;

    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.game_screen);
        frog = BitmapFactory.decodeResource(getResources(), R.drawable.game_screen);
    }
}
