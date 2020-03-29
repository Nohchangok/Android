package com.example.mywebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class MsgActivity extends AppCompatActivity {

    EditText msg;
    TextView size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);

        msg = findViewById(R.id.Message);
        size = findViewById(R.id.textsize);

        //보내기버튼 클릭 시 toast로 전달
        Button sendbtn = findViewById(R.id.sendbtn);
        sendbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = msg.getText().toString();
                Toast.makeText(MsgActivity.this, "Message : "+message, Toast.LENGTH_SHORT).show();
            }
        });


        //텍스트창이 바뀔때마다 실행.
        msg.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            //텍스트창이 바뀌면 글자크기를 넣어줌.
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



                //글자크기 표시
                try {
                    byte[] bytetext = msg.getText().toString().getBytes("KSC5601");
                    int cnt = bytetext.length;
                    size.setText(cnt+"/80 byte");

                    if(cnt%16==0){
                        msg.setText(msg.getText().toString() + "\n");
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

            //80자 이상일 경우 삭제.
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    byte[] byteText = s.toString().getBytes("KSC5601");
                    int cnt = byteText.length;
                    if (cnt > 80) {
                        s.delete(cnt - 2, cnt - 1);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        //돌아가기.
        Button gomain = findViewById(R.id.gohome);
        gomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
