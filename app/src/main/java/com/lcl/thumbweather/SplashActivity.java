package com.lcl.thumbweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {
    private LinearLayout rootLayout;
    private static final int sleepTime = 2500;
    ImageView imageView;

    private Handler MainHandler = new Handler() ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rootLayout =(LinearLayout)findViewById(R.id.splash_root);
        AlphaAnimation animation = new AlphaAnimation(0.3f,1.0f);
        animation.setDuration(2000);
        rootLayout.startAnimation(animation);



        MainHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },sleepTime);

//        imageView = (ImageView)findViewById(R.id.img);
//        animation = new RotateAnimation(0,180, AlphaAnimation.RELATIVE_TO_SELF,1.0f, Animation.RELATIVE_TO_SELF,1.0f);
//        animation.setDuration(3000);
//        imageView.startAnimation(animation);
    }
}
