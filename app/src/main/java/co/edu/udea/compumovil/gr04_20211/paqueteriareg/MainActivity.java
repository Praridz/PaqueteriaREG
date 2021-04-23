package co.edu.udea.compumovil.gr04_20211.paqueteriareg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private final int DURACION_SPLASH = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSplash();
    }

    private void getSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent myIntent  = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(myIntent);
                finish();
            }
        }, DURACION_SPLASH);
    }
}