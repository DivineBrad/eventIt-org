package com.example.bradj.eventitorg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    public void onClickLogin(View view){
//        Intent myIntent = new Intent(SplashActivity.this,
//                LoginActivity.class);
//        startActivity(myIntent);
        Intent myIntent = new Intent(SplashActivity.this, ViewEventsActivity.class);
        startActivity(myIntent);

    }

    public void onClickRegister(View view){
        Intent myIntent = new Intent(SplashActivity.this,
                RegisterActivity.class);
        startActivity(myIntent);

    }
}
