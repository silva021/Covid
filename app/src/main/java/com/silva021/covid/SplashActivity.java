package com.silva021.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.silva021.covid.Main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    ImageView   imgSplash;
    Animation   rotation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imgSplash = findViewById(R.id.imgSplash);
        rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotation);
        imgSplash.startAnimation(rotation);


        new Handler().postDelayed(() -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }, 4000);

    }

}