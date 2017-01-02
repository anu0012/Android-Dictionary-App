package com.darkguyy.anuragsharma.enggionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t=new Thread(){
            public void run(){

                try {
                    sleep(2000);

                    Intent intent=new Intent(MainActivity.this,home.class);
                    startActivity(intent);

                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        t.start();
    }
}
