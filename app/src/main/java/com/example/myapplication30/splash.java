package com.example.myapplication30;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class splash extends AppCompatActivity {
    private static int S_S = 5000;
    Animation topanim;
    ImageView image;
    TextView logo, slogan;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_anim1);
        image = findViewById(R.id.imageView);
        logo = findViewById(R.id.textView);
        image.setAnimation(topanim);
        logo.setAnimation(topanim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();



            }

        },S_S);

    }
}