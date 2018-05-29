package com.example.sky_lt.tbe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class StageSelectActivity extends Activity {

    Rect _Stage01 = new Rect(150, 320, 870, 620);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new StageSelectView(this));
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        //if (event.getAction() == MotionEvent.ACTION_DOWN) {
        if (_Stage01.contains((int)event.getX(), (int)event.getY())) {
            Toast.makeText(this, "Game Starts in 3 Second!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), InGameActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}
