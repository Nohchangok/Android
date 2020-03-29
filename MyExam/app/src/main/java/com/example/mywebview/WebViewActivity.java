package com.example.mywebview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        urlText = findViewById(R.id.editText4);
        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());

        Button goBackToMain = findViewById(R.id.button);
        goBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button showWebView = findViewById(R.id.button8);
        showWebView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {

                webView.loadUrl(urlText.getText().toString());
            }
        });

    }
    private class ViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("TEST","들어오는거니?");
            view.loadUrl(url);
            return true;
        }
    }
}
