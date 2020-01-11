package com.iucproject.mobilenews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {

    private WebView webview;
    private String webUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webview = findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webUrl= getIntent().getStringExtra("url");
        webview.loadUrl(webUrl);
        webview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack())
            webview.goBack();
        else
            super.onBackPressed();
    }
}
