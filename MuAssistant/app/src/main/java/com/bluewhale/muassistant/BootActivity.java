package com.bluewhale.muassistant;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.bluewhale.muassistant.login.LoginActivity;

public class BootActivity extends AppCompatActivity {


    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            Intent loginIntent  = new Intent(BootActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
        hide();
    }

    /***
     * 视图加载完成之后获取视图View
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Handler().postDelayed(mHideRunnable,2000);
    }

    private void hide() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

    }

}
