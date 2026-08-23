package com.roblesantoneli.vancontrolpro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        webView.setWebViewClient(
            new WebViewClient()
        );

        setContentView(webView);

webView.loadUrl(
    "https://script.google.com/macros/s/AKfycbxM7vqTgNJwv5eyoFITjGy-vd16PKCYZtQfT1nqaSTszFsia8CYb5InRwEu0IMBjwC4/exec"
);
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}