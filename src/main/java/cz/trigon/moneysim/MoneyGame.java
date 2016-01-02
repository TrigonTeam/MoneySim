package cz.trigon.moneysim;

import android.opengl.GLES20;

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
    boolean wasTouched = false;

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
        this.renderer.vertex(0, 0);
        this.renderer.vertex(100, 0);
        this.renderer.vertex(0, 100);
        this.renderer.flush();
    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
