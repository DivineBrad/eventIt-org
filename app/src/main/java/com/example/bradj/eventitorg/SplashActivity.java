package com.example.bradj.eventitorg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bradj.eventitorg.Utilities.LoginUtil;

public class SplashActivity extends AppCompatActivity {

    private LoginUtil loginUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loginUtil= LoginUtil.getInstance();

        if (loginUtil.isLoggedIn(getApplicationContext())){
            Intent intent = new Intent(getApplicationContext(), ViewEventsActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void onClickLogin(View view){
//        Intent myIntent = new Intent(SplashActivity.this,
//                LoginActivity.class);
//        startActivity(myIntent);
        Intent myIntent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(myIntent);

    }

    public void onClickRegister(View view){
        Intent myIntent = new Intent(SplashActivity.this,
                RegisterActivity.class);
        startActivity(myIntent);

    }
}
