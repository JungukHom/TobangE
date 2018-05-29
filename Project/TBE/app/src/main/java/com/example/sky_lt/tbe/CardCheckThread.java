package com.example.sky_lt.tbe;

/**
 * Created by sky_LT on 2017-06-20.
 */

public class CardCheckThread extends Thread {

    InGameView inGameView;

    public CardCheckThread(InGameView _view) {
        inGameView = _view;
    }

    public void run() {
        while (true) {
            inGameView.CheckCardRight();
        }
    }
}
