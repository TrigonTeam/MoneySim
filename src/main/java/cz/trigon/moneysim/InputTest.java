package cz.trigon.moneysim;

import android.opengl.GLES20;
import android.view.MotionEvent;

import java.io.IOException;

import cz.trigon.bicepsrendererapi.game.Game;
import cz.trigon.bicepsrendererapi.gl.interfaces.render.IImmediateRenderer;
import cz.trigon.bicepsrendererapi.gl.render.ImmediateRenderer;
import cz.trigon.bicepsrendererapi.managers.input.InputManager;
import cz.trigon.bicepsrendererapi.obj.Input;

public class InputTest extends Game {

    private InputManager input;
    private ImmediateRenderer renderer;

    protected InputTest() {

    }

    @Override
    public void setup() {
        try {
            this.surface.getContent().load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.input = this.surface.getInput();
        this.renderer = new ImmediateRenderer(this.surface);
    }

    @Override
    public void tick(int ticks) {

    }

    @Override
    public void renderTick(float ptt) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        float red = ImmediateRenderer.packColor(255, 0, 0, 255);
        this.renderer.color(red);
        //this.renderer.setPrimitiveMode(IImmediateRenderer.PrimitiveMode.LINES);

        this.renderer.setLineWidth(2);

        MotionEvent e = this.input.getLastEvent();
        if(e != null) {
            for (int i = 0; i < e.getPointerCount(); i++) {
                this.renderer.vertex(e.getX(i) * this.surface.getCanvasRatio(), 50);
                this.renderer.vertex(this.surface.getCanvasWidth(), 50);
                this.renderer.vertex(e.getX(i) * this.surface.getCanvasRatio(), 0);

                //this.renderer.vertex(e.getX(i) * this.surface.getCanvasRatio(), this.surface.getCanvasHeight());
                //this.renderer.vertex(0, e.getY(i) * this.surface.getCanvasRatio());
                //this.renderer.vertex(this.surface.getCanvasWidth(), e.getY(i) * this.surface.getCanvasRatio());
            }
        }

        this.renderer.flush();
    }

    @Override
    public void surfaceChanged(int w, int h) {

    }
}
