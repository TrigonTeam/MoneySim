package cz.trigon.moneysim;

import android.opengl.GLES20;

import cz.trigon.bicepsrendererapi.game.Game;

public class MoneyGame extends Game {

    @Override
    public void setup() {
        GLES20.glClearColor(0, 1, 0, 1);
    }

    @Override
    public void tick() {

    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
