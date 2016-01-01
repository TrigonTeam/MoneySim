package cz.trigon.moneysim;

import android.opengl.GLES20;

import java.io.IOException;
import java.util.Random;

import cz.trigon.bicepsrendererapi.game.Game;
import cz.trigon.bicepsrendererapi.obj.Content;
import cz.trigon.bicepsrendererapi.obj.Music;
import cz.trigon.bicepsrendererapi.obj.SoundEffect;

public class MoneyGame extends Game {

    private Random rnd = new Random();
    private Content content;
    private SoundEffect se;
    private Music m;

    @Override
    public void setup() {
        GLES20.glClearColor(0, 1, 0, 1);

        try {
            this.content = new Content();
            this.content.load();
            this.se = this.content.get("/default.sounds/table-hit.mp3", SoundEffect.class);
            this.m = this.content.get("/default.music/digeridoo.mp3", Music.class);
            this.m.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick(int ticks) {
        if (ticks % (60 * 4) == 0) {
            this.se.play(rnd.nextFloat());
            GLES20.glClearColor(rnd.nextFloat(), rnd.nextFloat(), rnd.nextFloat(), 1f);

            if (this.m.isPaused())
                this.m.resume();
            else
                this.m.pause();
        }
    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
