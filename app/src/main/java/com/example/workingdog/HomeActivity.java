package com.example.workingdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.workingdog.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 4000;
    private Button button;
    //variables
    private Animation topAnim, bottomAnim;
    private ImageView image;
    private TextView titel,subtitle ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);
        //Animation
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        //hooks
        image = findViewById(R.id.imageLetsDoThis);
       // welcomeText = findViewById(R.id.titleWelcome);
        titel = findViewById(R.id.titleWelcome);
        subtitle = findViewById(R.id.summaryApp);

        image.setAnimation(bottomAnim);
       // welcomeText.setAnimation(topAnim);
        titel.setAnimation(topAnim);
        subtitle.setAnimation(topAnim);

        button = findViewById(R.id.buttonLetsGetStarted);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHomeActivity2();
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeIntent = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(homeIntent);
//                finish();
//            }
//        },SPLASH_TIME_OUT);
    }

    private void openHomeActivity2() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}