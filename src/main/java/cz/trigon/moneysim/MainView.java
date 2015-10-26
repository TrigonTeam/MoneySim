package cz.trigon.moneysim;

import android.content.Context;
import android.opengl.GLSurfaceView;

import cz.trigon.bicepsrendererapi.SurfaceRenderer;

public class MainView extends GLSurfaceView {

    private final SurfaceRenderer renderer;

    public MainView(Context context){
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        MoneyGame game = new MoneyGame();

        renderer = new SurfaceRenderer(context, game);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(this.renderer);
    }
}