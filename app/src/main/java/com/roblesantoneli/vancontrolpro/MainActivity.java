package com.roblesantoneli.vancontrolpro;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

    private WebView webView;

    private FrameLayout container;

    private LinearLayout splash;

    private static final String URL_VANCONTROL =
        "https://script.google.com/macros/s/AKfycbxM7vqTgNJwv5eyoFITjGy-vd16PKCYZtQfT1nqaSTszFsia8CYb5InRwEu0IMBjwC4/exec";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /*
         * =====================================
         * TELA CHEIA / ÁREA SEGURA
         * =====================================
         */
        Window window =
            getWindow();

        window.setStatusBarColor(
            Color.rgb(
                17,
                17,
                15
            )
        );

        window.setNavigationBarColor(
            Color.rgb(
                17,
                17,
                15
            )
        );


        /*
         * =====================================
         * CONTAINER PRINCIPAL
         * =====================================
         */
        container =
            new FrameLayout(this);


        /*
         * =====================================
         * TELA DE ABERTURA
         * =====================================
         */
        splash =
            new LinearLayout(this);

        splash.setOrientation(
            LinearLayout.VERTICAL
        );

        splash.setGravity(
            Gravity.CENTER
        );

        splash.setPadding(
            40,
            40,
            40,
            40
        );

        splash.setBackgroundColor(
            Color.rgb(
                17,
                17,
                15
            )
        );


        TextView titulo =
            new TextView(this);

        titulo.setText(
            "VANCONTROL PRO"
        );

        titulo.setTextColor(
            Color.rgb(
                230,
                161,
                92
            )
        );

        titulo.setTextSize(
            24
        );

        titulo.setGravity(
            Gravity.CENTER
        );


        TextView subtitulo =
            new TextView(this);

        subtitulo.setText(
            "Robles & Antoneli"
        );

        subtitulo.setTextColor(
            Color.WHITE
        );

        subtitulo.setTextSize(
            15
        );

        subtitulo.setGravity(
            Gravity.CENTER
        );

        subtitulo.setPadding(
            0,
            10,
            0,
            24
        );


        ProgressBar progressBar =
            new ProgressBar(this);


        TextView carregando =
            new TextView(this);

        carregando.setText(
            "Carregando..."
        );

        carregando.setTextColor(
            Color.LTGRAY
        );

        carregando.setTextSize(
            13
        );

        carregando.setGravity(
            Gravity.CENTER
        );

        carregando.setPadding(
            0,
            18,
            0,
            0
        );


        splash.addView(
            titulo
        );

        splash.addView(
            subtitulo
        );

        splash.addView(
            progressBar
        );

        splash.addView(
            carregando
        );


        /*
         * =====================================
         * WEBVIEW
         * =====================================
         */
        webView =
            new WebView(this);


        WebSettings webSettings =
            webView.getSettings();


        webSettings.setJavaScriptEnabled(
            true
        );

        webSettings.setJavaScriptCanOpenWindowsAutomatically(
            true
        );

        webSettings.setDomStorageEnabled(
            true
        );

        webSettings.setDatabaseEnabled(
            true
        );

        webSettings.setLoadWithOverviewMode(
            false
        );

        webSettings.setUseWideViewPort(
            false
        );

        webSettings.setCacheMode(
            WebSettings.LOAD_DEFAULT
        );

        webSettings.setLoadsImagesAutomatically(
            true
        );

        webSettings.setMediaPlaybackRequiresUserGesture(
            false
        );


        /*
         * =====================================
         * COOKIES
         * =====================================
         */
        CookieManager cookieManager =
            CookieManager.getInstance();

        cookieManager.setAcceptCookie(
            true
        );

        cookieManager.setAcceptThirdPartyCookies(
            webView,
            true
        );


        /*
         * =====================================
         * CLIENTE WEB
         * =====================================
         */
        webView.setWebViewClient(
            new WebViewClient() {

                @Override
                public void onPageFinished(
                    WebView view,
                    String url
                ) {

                    super.onPageFinished(
                        view,
                        url
                    );

                    splash.setVisibility(
                        View.GONE
                    );

                    webView.setVisibility(
                        View.VISIBLE
                    );
                }
            }
        );


        webView.setWebChromeClient(
            new WebChromeClient()
        );


        /*
         * =====================================
         * MONTA A TELA
         * =====================================
         */
        webView.setVisibility(
            View.INVISIBLE
        );


        container.addView(
            webView,
            new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        );


        container.addView(
            splash,
            new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        );


        setContentView(
            container
        );


        /*
         * =====================================
         * CARREGA O VANCONTROL
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
