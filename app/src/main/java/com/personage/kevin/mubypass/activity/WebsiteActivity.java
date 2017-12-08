package com.personage.kevin.mubypass.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.personage.kevin.mubypass.Fragment.BaseFragment;
import com.personage.kevin.mubypass.R;
import com.personage.kevin.mubypass.view.ProgressWebView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * 私服网站主页展示
 * Created by pc1 on 2016/10/31.
 */

public class WebsiteActivity extends BaseActivity {

    public static final String LINK_BUNDLE="LINK_BUNDLE";
    public static final String LINK_ADDRESS="LINK_ADDRESS";
    public static final String GAME_NAME = "GAME_NAME";
    private final String WWW = "www.";
    private final String HTTP = "http://";
    private String linkStr ;
    private String gameName ;
    private ProgressWebView webView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.website_content_fragment);
        initIntent();
        initTitle();
        initWebSetting();
    }

    private void initTitle() {
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.BLACK);
        toolbar = (Toolbar)findViewById(R.id.web_tool_bar);
        toolbar.setTitle(gameName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    private void initIntent(){
        Bundle bundle = getIntent().getBundleExtra(LINK_BUNDLE);
        linkStr = bundle.getString(LINK_ADDRESS);
        if(linkStr.contains(WWW)){
            linkStr = linkStr.replace(WWW,HTTP);
        }
        gameName= bundle.getString(GAME_NAME);
    }

    private void initWebSetting(){
        webView = (ProgressWebView) findViewById(R.id.web_view);
        webView.setWebViewClient(new MyWebViewClient());

        //为WebView设置WebViewClient处理某些操作
        WebSettings s = webView.getSettings();
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);
        s.setLoadWithOverviewMode(true);
        s.setSaveFormData(true);
        s.setJavaScriptEnabled(true);
        s.setDefaultTextEncodingName("utf-8");
        // enable navigator.geolocation
        // 关闭缩放
        s.setBuiltInZoomControls(false);
        s.setSupportZoom(false);
        s.setDisplayZoomControls(false);

        s.setCacheMode(WebSettings.LOAD_DEFAULT);
//        String cacheDirPath = getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME;
        s.setGeolocationEnabled(true);
//        s.setGeolocationDatabasePath(cacheDirPath);
//        s.setAppCachePath(cacheDirPath);
        s.setAppCacheEnabled(true);
        // enable Web Storage: localStorage, sessionStorage
        s.setDomStorageEnabled(true);
        webView.requestFocus();
        webView.setScrollContainer(true);
        webView.loadUrl(linkStr);
    }

    class MyWebViewClient extends WebViewClient {

        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            view.loadUrl(url);
            //如果不需要其他对点击链接事件的处理返回true，否则返回false

            return true;

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
