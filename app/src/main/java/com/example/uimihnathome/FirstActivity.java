package com.example.uimihnathome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sharedpreferences.SharedPreferencesManager;
import com.example.uimihnathome.Activity.IntroActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Thread timeS=new Thread(){
            public void run()
            {
                try
                {
                    sleep(5000); // thoi gian chuyen quan man hinh khac
                } catch (Exception e) {

                }
                finally
                {

                    if (SharedPreferencesManager.isFirstTimeSetup()==true){
                        Intent intent = new Intent(FirstActivity.this, IntroActivity.class);
                        startActivity(intent);
                    }
                    else if (SharedPreferencesManager.isFirstTimeSetup()==false){
                        Intent intent = new Intent(FirstActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                    finish();
                }
            }
        };
        timeS.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
