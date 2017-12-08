package com.personage.kevin.mubypass.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.personage.kevin.mubypass.R;

/**
 * Created by pc1 on 2016/10/18.
 */
public class BootActivity extends BaseActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View bootView = View.inflate(this, R.layout.boot_layout,null);
        setContentView(bootView);
        AlphaAnimation animation = new AlphaAnimation(0.3f,1.0f);
        animation.setDuration(2000);
        bootView.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void redirectTo(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
