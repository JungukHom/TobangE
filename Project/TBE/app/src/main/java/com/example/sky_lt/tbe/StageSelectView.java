package com.example.sky_lt.tbe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class StageSelectView extends View {

    DisplayMetrics dm;
    int scWidth, scHeight;
    Bitmap stageSelect, _stageSelect;

    public StageSelectView (Context context) {
        super(context);

        dm = context.getResources().getDisplayMetrics();
        scWidth = dm.widthPixels;
        scHeight = dm.heightPixels;
        stageSelect = BitmapFactory.decodeResource(getResources(), R.drawable.stageselect);
        _stageSelect = Bitmap.createScaledBitmap(stageSelect, scWidth, scHeight, false);
        stageSelect.recycle();
    }
    @Override
    protected void onDraw (Canvas canvas) {
        canvas.drawBitmap(_stageSelect, 0, 0, null);
    }
}
