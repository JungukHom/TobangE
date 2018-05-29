package com.example.sky_lt.tbe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class MainView extends View {

    DisplayMetrics dm;
    int scWidth, scHeight;
    Bitmap main1, _main1;

    public MainView(Context context) {
        super(context);
        dm = context.getResources().getDisplayMetrics();
        scWidth = dm.widthPixels;
        scHeight = dm.heightPixels;
        main1 = BitmapFactory.decodeResource(getResources(), R.drawable.main);
        _main1 = Bitmap.createScaledBitmap(main1, scWidth, scHeight, false);
        main1.recycle();
    }

    @Override
    protected void onDraw (Canvas canvas) {
        canvas.drawBitmap(_main1, 0, 0, null);
    }
}
