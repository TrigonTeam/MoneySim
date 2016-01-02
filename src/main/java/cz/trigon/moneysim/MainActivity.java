package cz.trigon.moneysim;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cz.trigon.bicepsrendererapi.game.Surface;

public class MainActivity extends Activity {

    private String s = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        RelativeLayout rl = new RelativeLayout(this);
        RelativeLayout.LayoutParams lp =
                new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_TOP);

        final TextView tvFps = new TextView(this);
        //final MoneyGame m = new MoneyGame();
        final InputTest m = new InputTest();
        final Surface view = new Surface(this, m);
        /*m.setInfoCallback(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.this.s = ", build: " + m.buildMs + ", flush: " + m.flushMs;
                    }
                });
            }
        });*/

        rl.addView(view);

        tvFps.setLayoutParams(lp);
        tvFps.setTextColor(Color.WHITE);

        view.setFpsCallback(new Runnable() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvFps.setText("FPS: " + view.getFps() + MainActivity.this.s);
                    }
                });
            }
        });

        rl.addView(tvFps);
        this.setContentView(rl);
    }

}