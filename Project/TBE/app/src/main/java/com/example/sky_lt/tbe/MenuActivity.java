package com.example.sky_lt.tbe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class MenuActivity extends Activity {
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sara_menu);
    }

    public void mOnClick(View v) {
        switch ( v.getId()) {
            case R.id.sara_return:
                startActivity(new Intent(this, InGameView.class));
                break;
            case R.id.sara_home:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.sara_cancel:
                finish();
                break;
        }
    }
}
