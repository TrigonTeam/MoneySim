package cz.trigon.moneysim;

import android.opengl.GLES20;

import java.io.IOException;
import java.util.Random;

import cz.trigon.bicepsrendererapi.game.Game;
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
    private boolean wasTouched = false;
    private Runnable infoCallback;
    float buildMs, flushMs;

    protected void setInfoCallback(Runnable c) {
        this.infoCallback = c;
    }

    @Override
    public void setup() {
        GLES20.glClearColor(0, 1, 0, 1);

        try {
            this.content = new Content();
            this.content.load();
            this.se = this.content.get("/default.sounds/table-hit.mp3", SoundEffect.class);
            this.m = this.content.get("/default.music/digeridoo.mp3", Music.class);
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

            if (!m.isPlaying())
                m.play();

            if (!wasTouched) {
                se.play();
                wasTouched = true;
            }
        } else if (wasTouched) {
            wasTouched = false;
        }
    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        float red = ImmediateRenderer.packColor(255, 0, 0, 255);
        float green = ImmediateRenderer.packColor(0, 255, 0, 255);
        float blue = ImmediateRenderer.packColor(0, 0, 255, 255);

        long start = System.nanoTime();
        for (int x = 0; x < 108; x++) {
            for (int y = 0; y < 192; y++) {
                this.renderer.color(red);
                this.renderer.vertex(x*10, y*10);
                this.renderer.color(green);
                this.renderer.vertex(10+x*10, y*10);
                this.renderer.color(blue);
                this.renderer.vertex(x*10, 10+y*10);
            }
        }

        this.buildMs = (System.nanoTime() - start) / 1000000f;
        start = System.nanoTime();
        this.renderer.flush();
        this.flushMs = (System.nanoTime() - start) / 1000000f;
        this.infoCallback.run();
    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
