package com.roblesantoneli.vancontrolpro;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    private static final String URL_VANCONTROL =
        "https://script.google.com/macros/s/AKfycbxM7vqTgNJwv5eyoFITjGy-vd16PKCYZtQfT1nqaSTszFsia8CYb5InRwEu0IMBjwC4/exec";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        webView =
            new WebView(this);


        WebSettings webSettings =
            webView.getSettings();


        /*
         * =====================================
         * JAVASCRIPT
         * =====================================
         */
        webSettings.setJavaScriptEnabled(true);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(
            true
        );


        /*
         * =====================================
         * ARMAZENAMENTO
         * =====================================
         */
        webSettings.setDomStorageEnabled(true);

        webSettings.setDatabaseEnabled(true);


        /*
         * =====================================
         * RESPONSIVIDADE
         * =====================================
         */
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setUseWideViewPort(true);


        /*
         * =====================================
         * CACHE
         * =====================================
         *
         * Ajuda a deixar as próximas
         * aberturas mais rápidas.
         */
        webSettings.setCacheMode(
            WebSettings.LOAD_DEFAULT
        );


        /*
         * =====================================
         * COOKIES
         * =====================================
         *
         * Muito importante para
         * Google Apps Script.
         */
        CookieManager cookieManager =
            CookieManager.getInstance();

        cookieManager.setAcceptCookie(
            true
        );

        CookieManager.setAcceptFileSchemeCookies(
            true
        );

        cookieManager.setAcceptThirdPartyCookies(
            webView,
            true
        );


        /*
         * =====================================
         * WEBVIEW CLIENT
         * =====================================
         *
         * Mantém a navegação dentro
         * do aplicativo.
         */
        webView.setWebViewClient(
            new WebViewClient()
        );


        /*
         * =====================================
         * WEB CHROME CLIENT
         * =====================================
         *
         * Necessário para recursos
         * mais completos de JavaScript.
         */
        webView.setWebChromeClient(
            new WebChromeClient()
        );


        /*
         * =====================================
         * MOSTRA O WEBVIEW
         * =====================================
         */
        setContentView(
            webView
        );


        /*
         * =====================================
         * ABRE O VANCONTROL
         * =====================================
         */
        webView.loadUrl(
            URL_VANCONTROL
        );
    }


    @Override
    public void onBackPressed() {

        if (
            webView != null &&
            webView.canGoBack()
        ) {

            webView.goBack();

        } else {

            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {

        if (webView != null) {

            webView.destroy();

            webView =
                null;
        }

        super.onDestroy();
    }
}
