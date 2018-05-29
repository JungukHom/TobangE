package com.example.sky_lt.tbe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class InGameView extends View {

    DisplayMetrics dm;
    Random ran;
    int[][] imageBoard, numberBoard;
    Rect[][] rect;
    int left, top; // 게임 시작할 왼족 위 좌표
    int imageSize, scWidth, scHeight; // 이지미와 스크린 사이즈
    Bitmap bunny, _bunny, carrot, _carrot, clo, _clo, heart, _heart, backImage, _backImage, isPress, _isPress;
    Bitmap stage01, _stage01, clear, _clear;
    Rect _rect;
    int rightCount = 0;
    int firstTouch_I  = -100, firstTouch_J = -100, secondTouch_I = -100, secondTouch_J = -100;
    final int GAME_READY = 0;
    final int GAME_START = 1;
    final int FINISHED = 2;
    int state = GAME_READY;
    final int NO = 0;
    final int YES = 1;
    int clearState = NO;

    InGameActivity inGameActivity;

    public InGameView (Context context) {
        super(context);
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inSampleSize = 1;
        option.inPurgeable = true;
        option.inDither = true;
        dm = context.getResources().getDisplayMetrics();
        scWidth = dm.widthPixels;
        scHeight = dm.heightPixels;
        imageSize = (int) ((scWidth * 0.7) - 30) / 4;
        stage01 = BitmapFactory.decodeResource(getResources(), R.drawable.stage01);
        _stage01 = Bitmap.createScaledBitmap(stage01, scWidth, scHeight, false);
        stage01.recycle();
        clear = BitmapFactory.decodeResource(getResources(), R.drawable.clear);
        _clear = Bitmap.createScaledBitmap(clear, scWidth, scHeight, false);
        clear.recycle();
        bunny = BitmapFactory.decodeResource(getResources(), R.drawable.cardbunny);
        _bunny = Bitmap.createScaledBitmap(bunny, imageSize, imageSize, false);
        bunny.recycle();
        carrot = BitmapFactory.decodeResource(getResources(), R.drawable.cardcarrot);
        _carrot = Bitmap.createScaledBitmap(carrot, imageSize, imageSize, false);
        carrot.recycle();
        clo = BitmapFactory.decodeResource(getResources(), R.drawable.cardclober);
        _clo = Bitmap.createScaledBitmap(clo, imageSize, imageSize, false);
        clo.recycle();
        heart = BitmapFactory.decodeResource(getResources(), R.drawable.cardheart);
        _heart = Bitmap.createScaledBitmap(heart, imageSize, imageSize, false);
        heart.recycle();
        backImage = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        _backImage = Bitmap.createScaledBitmap(backImage, imageSize, imageSize, false);
        backImage.recycle();
        isPress = BitmapFactory.decodeResource(getResources(), R.drawable.ispressed);
        _isPress = Bitmap.createScaledBitmap(isPress, imageSize, imageSize, false);
        isPress.recycle();

        // 사각형 만들기
        left = (int) (scWidth * 0.15);
        top = (int) (scHeight * 0.3);
        rect = new Rect[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int r_left = left + ((imageSize + 10) * j);
                int r_top = top + ((imageSize + 10) * i);
                int r_right = left + ((imageSize + 10) * (j + 1));
                int r_bottom = top + ((imageSize + 10) * (i + 1));
                rect[i][j] = new Rect(r_left, r_top, r_right, r_bottom);
            } // for
        } // for

        CardShuffle();

        // 이미지들을 모두 뒷면 이미지로 지정
        imageBoard = new int[4][4];
        AnswerShow();

        CardCheckThread _thread = new CardCheckThread(this);
        _thread.start();

        ShowAnswerThread _thread2 = new ShowAnswerThread(this);
        _thread2.start();
    }

    public void AnswerShow () {
        if (state == GAME_READY) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    imageBoard[i][j] = numberBoard[i][j];
                } // for
            } // for
            state = GAME_START;
        }
        else if (state == GAME_START) {
            try {
                Thread.sleep(3000);
            }
            catch ( InterruptedException e ) {
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    imageBoard[i][j] = 0;
                } // for
            } // for
            state = FINISHED;
        }
        else if (state == FINISHED) {
            return;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // 그림이 시작할 곳 지정
        left = (int) (scWidth * 0.15);
        top = (int) (scHeight * 0.30);

        if (clearState == NO) {
            // 배경 이미지 출력
            canvas.drawBitmap(_stage01, 0, 0, null);

            // 화면 중앙부근에 이미지 출력
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (imageBoard[i][j] == 0)
                        canvas.drawBitmap(_backImage, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    else if (imageBoard[i][j] == 1)
                        canvas.drawBitmap(_bunny, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    else if (imageBoard[i][j] == 2)
                        canvas.drawBitmap(_carrot, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    else if (imageBoard[i][j] == 3)
                        canvas.drawBitmap(_clo, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    else if (imageBoard[i][j] == 4)
                        canvas.drawBitmap(_heart, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    else if (imageBoard[i][j] == -50) {
                        canvas.drawBitmap(_isPress, left + (imageSize + 10) * j, top + (imageSize + 10) * i, null);
                    }
                } // for
            } // for
        }
        else if (clearState == YES) {
            // 화면 잠시 정지
            try {
                Thread.sleep(500);
            }
            catch ( InterruptedException e ) {
            }
            // 클리어 이미지 출력
            canvas.drawBitmap(_clear, 0, 0, null);
        }
        invalidate();
    } // onDraw

    // Random 수 지정
    public void CardShuffle() {
        ran = new Random();
        final int BUNNY = 1;
        final int CARROT = 2;
        final int CLOBER = 3;
        final int HEART = 4;
        int[] elements = {BUNNY, BUNNY, BUNNY, BUNNY, CARROT, CARROT, CARROT, CARROT,
                CLOBER, CLOBER, CLOBER, CLOBER, HEART, HEART, HEART, HEART};
        numberBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                while (true) {
                    int r = ran.nextInt(16);
                    if (elements[r] == 0) {
                        continue;
                    } else {
                        numberBoard[i][j] = elements[r];
                        elements[r] = 0;
                        break;
                    } // if - else
                } // while (true)
            } // for
        } // for
    } // CardShuffle

    public boolean onTouchEvent(MotionEvent event) {
        if ( event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int i, j;
            for (int idx = 0; idx < 16; idx++) {
                i = idx / 4;
                j = idx % 4;
                _rect = rect[i][j];
                if (_rect.contains(x, y)) {
                    if (imageBoard[i][j] != -50) {
                        imageBoard[i][j] = numberBoard[i][j];
                        if (firstTouch_I == -100 && firstTouch_J == -100) { // fI값과 fJ값의 값이 지정돼있지 않으면
                            firstTouch_I = i;
                            firstTouch_J = j;
                        } else if (firstTouch_I != -100 && firstTouch_J != -100) { // fI와 fJ에 값이 들어가 있으면,
                            secondTouch_I = i;
                            secondTouch_J = j;
                        } // else if
                    } // if
                } // if
            } // for
        }
        return true;
    } // onTouchEvent

    public void CheckCardRight () {
        if (firstTouch_I == -100 || firstTouch_J == -100 || secondTouch_I == -100 || secondTouch_J == -100) {
            return;
        }
        if (numberBoard[firstTouch_I][firstTouch_J] == numberBoard[secondTouch_I][secondTouch_J]) {
            try {
                Thread.sleep(250);
            }
            catch (InterruptedException exception) {
            }
            imageBoard[firstTouch_I][firstTouch_J] = -50;
            imageBoard[secondTouch_I][secondTouch_J] = -50;
            firstTouch_I = -100;
            firstTouch_J = -100;
            secondTouch_I = -100;
            secondTouch_J = -100;
            rightCount++;
            if (rightCount == 8) {
                clearState = YES;
            }
        }
        else {
            try {
                Thread.sleep(500);
            }
            catch (InterruptedException exception) {
            }
            imageBoard[firstTouch_I][firstTouch_J] = 0;
            imageBoard[secondTouch_I][secondTouch_J] = 0;
            firstTouch_I = -100;
            firstTouch_J = -100;
            secondTouch_I = -100;
            secondTouch_J = -100;
        }
    }
}
