package cz.trigon.moneysim;

import android.opengl.GLES20;

import java.util.Random;

import cz.trigon.bicepsrendererapi.game.Game;
import cz.trigon.bicepsrendererapi.gl.impl.render.Renderer;

public class MoneyGame extends Game {

    private Renderer renderer;

    @Override
    public void setup() {
        GLES20.glClearColor(0, 0, 0, 1);
        this.renderer = new Renderer();
    }

    @Override
    public void tick() {

    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        this.renderer.vertex(0, 0);
        this.renderer.vertex(100, 0);
        this.renderer.vertex(0, 100);
        this.renderer.flush();

    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
