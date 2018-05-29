package com.example.sky_lt.tbe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class InGameActivity extends Activity {

    // public Rect _menuRect = new Rect(600, 0, 1100, 300);
    // Intent intent1;

    InGameView inGameView;
    /*
    public InGameActivity (InGameView _view) {
        inGameView = _view;
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new InGameView(this));
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (inGameView.clearState == 0) {
                return false;
            }
            else if (inGameView.clearState == 1) {
                Intent intent = new Intent(getApplicationContext(), StageSelectActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return true;
    }

}
