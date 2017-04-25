package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cpfei.project.R;

public class WebViewActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);

        return intent;
    }


    protected WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView)findViewById(R.id.webView);

        settingWebView();
    }


    protected void settingWebView() {

        WebSettings webSettings = webView.getSettings();//获得WebView的设置
        webSettings.setUseWideViewPort(true);// 设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);//适配
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        webSettings.setDomStorageEnabled(true);// 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);//开启 database storage API 功能
//        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);//HTTPS，注意这个是在LOLLIPOP以上才调用的
        webSettings.setAppCacheEnabled(true);//开启 Application Caches 功能
        webSettings.setBlockNetworkImage(true);//关闭加载网络图片，在一开始加载的时候可以设置为true，当加载完网页的时候再设置为false

        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());

        webView.loadUrl("http://m.test.baicheng.com/Shopping");//WebView加载的网页使用loadUrl
    }


    class MyWebChromeClient extends WebChromeClient{

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //加载的进度
        }
        @Override
        public void onReceivedTitle(WebView view, String title) {
            //获取WebView的标题
        }
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
            //Js 弹框
        }
        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            return true;
        }


    }


    class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //需要设置在当前WebView中显示网页，才不会跳到默认的浏览器进行显示
            return true;
        }
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            //加载出错了
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            //加载完成
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (webView != null) {
//            webView.clearCache(true); //清空缓存
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                webView.removeAllViews();
//                webView.destroy();
//            }else {
//                webView.removeAllViews();
//                webView.destroy();
//            }
//            webView = null;
//        }


    }
}
