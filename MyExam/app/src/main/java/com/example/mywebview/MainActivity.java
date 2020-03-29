package com.example.mywebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gotoWebView = findViewById(R.id.button1);
        Button gotoYoutubeView = findViewById(R.id.gomain);
        Button gotoMsgView = findViewById(R.id.msgGo);

        //웹보기
        gotoWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        //유튜브 보기
        gotoYoutubeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), YoutubeExamActivity.class);

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        //메세지보내기
        gotoMsgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MsgActivity.class);

                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }



}
