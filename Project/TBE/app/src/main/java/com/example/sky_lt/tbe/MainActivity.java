package com.example.sky_lt.tbe;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static MediaPlayer _sara_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MainView(this));
        _sara_music = MediaPlayer.create(this, R.raw.bgm);
        _sara_music.setLooping(true);
        _sara_music.start();
    }

    @Override
    public boolean  onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Intent intent = new Intent(getApplicationContext(), StageSelectActivity.class);
            startActivity(intent);
            finish();
        }
        return false;
    }
}
