package cz.trigon.moneysim;

import android.opengl.GLES20;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

import cz.trigon.bicepsrendererapi.game.Game;
import cz.trigon.bicepsrendererapi.game.Surface;
import cz.trigon.bicepsrendererapi.gl.interfaces.render.IImmediateRenderer;
import cz.trigon.bicepsrendererapi.gl.render.ImmediateRenderer;
import cz.trigon.bicepsrendererapi.obj.Content;
import cz.trigon.bicepsrendererapi.obj.Input;
import cz.trigon.bicepsrendererapi.obj.Music;
import cz.trigon.bicepsrendererapi.obj.SoundEffect;

public class MoneyGame extends Game {

    private Random rnd = new Random();
    private Content content;
    private SoundEffect se;
    private Music m;

    private IImmediateRenderer renderer;

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

    this.renderer = new ImmediateRenderer(this.getSurface());


    }

    @Override
    public void tick(int ticks) {
        Input i = new Input();

        if (i.isTouched()) {
            GLES20.glClearColor(i.getTouchX() / this.surface.getCanvasWidth(),
                    i.getTouchY() / this.surface.getCanvasHeight(), (float) Math.sin(ticks / 60f), 1f);

            Log.d(Surface.LDTAG, "Touch " + i.getTouch().toString());
        }
    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        long start = System.nanoTime();
        for (int x = 0; x < 108; x++) {
            for (int y = 0; y < 192; y++) {
                this.renderer.color(1, 0, 0, 1);
                this.renderer.vertex(x*10, y*10);
                this.renderer.color(0, 1, 0, 1);
                this.renderer.vertex(10+x*10, y*10);
                this.renderer.color(0, 0, 1, 1);
                this.renderer.vertex(x*10, 10+y*10);
            }
        }
        Log.i(Surface.LDTAG, "Building took " + (System.nanoTime()-start)/1000000D + "ms");

        start = System.nanoTime();
        this.renderer.flush();
        Log.i(Surface.LDTAG, "Flushing took " + (System.nanoTime()-start)/1000000D + "ms");

    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
