package com.example.sky_lt.tbe;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class ShowAnswerThread extends Thread {
    InGameView inGameView;

    public ShowAnswerThread (InGameView _view) {
        inGameView = _view;
    }

    public void run() {
        while (true) {
            inGameView.AnswerShow();
        }
    }
}
