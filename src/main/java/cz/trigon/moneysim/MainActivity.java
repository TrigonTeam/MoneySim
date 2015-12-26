package cz.trigon.moneysim;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import java.io.IOException;

import cz.trigon.bicepsrendererapi.game.Surface;
import cz.trigon.bicepsrendererapi.obj.Content;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Surface view = new Surface(this, new MoneyGame());
        setContentView(view);

        Content c = new Content();

        try {
            c.load();
        } catch (IOException e) {
            Log.e(Surface.LDTAG, "Couldn't load content", e);
        }

        Content c2 = new Content();

        Log.d(Surface.LDTAG, "Dir count: " + c2.listDirectories("/").size());
    }

}