package cz.trigon.moneysim;

import android.app.Activity;
import android.content.res.AssetManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

import cz.trigon.bicepsrendererapi.Surface;
import cz.trigon.bicepsrendererapi.content.Content;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Surface view = new Surface(this, new MoneyGame());
        setContentView(view);

        Content c = new Content(this.getAssets());
        try {
            c.load();
        } catch (IOException e) {
            Log.e("contentTest", e.getMessage());
        }

        c.getPreloader();
    }

}