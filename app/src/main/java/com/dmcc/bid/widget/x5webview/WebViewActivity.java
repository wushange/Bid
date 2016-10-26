package com.dmcc.bid.widget.x5webview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.widget.AppTitle;
import com.mylhyl.acp.AcpListener;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.util.List;

import butterknife.BindView;

@SuppressLint("SetJavaScriptEnabled")
public class WebViewActivity extends BaseActivity {

    public static void startActivity(Context mContext, String url) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("htmlURL", url);
        mContext.startActivity(intent);
    }

    @Override
    public void initInjector() {

    }


    @BindView(R.id.root_webview_layout)
    FrameLayout frameLayout;
    WebView webView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.apptitle)
    AppTitle appTitle;
    String url;
    public static final int MSG_WEBVIEW_CONSTRUCTOR = 1;
    private long timerCounter;
    private Handler handler;

    @Override
    public int bindLayout() {
        return R.layout.webview_layout;
    }

    @Override
    public void initView(View view) {
        setSwipeBackEnable(true);
        appTitle.getmCenterTitle().setSingleLine(true);
        appTitle.getmCenterTitle().setEllipsize(TextUtils.TruncateAt.MARQUEE);
        appTitle.getmCenterTitle().setFocusable(true);
        appTitle.getmCenterTitle().setFocusableInTouchMode(true);
        appTitle.getmCenterTitle().setHorizontallyScrolling(true);
        appTitle.getmCenterTitle().setClickable(true);
        appTitle.getmCenterTitle().setMaxEms(11);
        appTitle.setCenterTitle("正在加载...");
        appTitle.setLeftButtonClickListener(v -> finish());

    }

    @Override
    public void doBusiness(Context mContext) {
        mBaseOperation.checkPermission(getContext(), new AcpListener() {
            @Override
            public void onGranted() {
                timerCounter = System.currentTimeMillis();
                handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(Message msg) {
                        // TODO Auto-generated method stub
                        switch (msg.what) {
                            case MSG_WEBVIEW_CONSTRUCTOR:
                                Logger.e("收到init完毕");
                                initWebView();
                                break;
                        }
                        super.handleMessage(msg);
                    }
                };


                preinitX5WebCore();
            }

            @Override
            public void onDenied(List<String> permissions) {

            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE);

    }

    /**
     * X5内核在使用preinit接口之后，对于首次安装首次加载没有效果
     * 实际上，X5webview的preinit接口只是降低了webview的冷启动时间；
     * 因此，现阶段要想做到首次安装首次加载X5内核，必须要让X5内核提前获取到内核的加载条件
     */
    private void preinitX5WebCore() {

        if (!QbSdk.isTbsCoreInited()) {//preinit只需要调用一次，如果已经完成了初始化，那么就直接构造view
            new Thread(() -> {
                QbSdk.preInit(WebViewActivity.this, myCallback);
            }).start();
            //设置X5初始化完成的回调接口  第三个参数为true：如果首次加载失败则继续尝试加载；
        } else {
            handler.sendEmptyMessageDelayed(MSG_WEBVIEW_CONSTRUCTOR, 500); //延时500ms的构建webview(为冷启动争取时间)
        }


    }


    private QbSdk.PreInitCallback myCallback = new QbSdk.PreInitCallback() {

        @Override
        public void onViewInitFinished() {//当X5webview 初始化结束后的回调
            // TODO Auto-generated method stub
            float deltaTime = (System.currentTimeMillis() - timerCounter) / 1000;
//            Toast.makeText(getContext(), "x5初始化使用了" + deltaTime + "秒", Toast.LENGTH_LONG).show();
            Logger.e("x5初始化使用了" + deltaTime + "秒");
            handler.sendEmptyMessageDelayed(MSG_WEBVIEW_CONSTRUCTOR, 500);
        }

        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            Logger.e("onCoreInitFinished");
        }


    };


    @SuppressLint("JavascriptInterface")
    @SuppressWarnings("deprecation")
    private void initWebView() {
        webView = new WebView(getContext());
        frameLayout.addView(webView);
        webView.addJavascriptInterface(new WebViewJavaScriptFunction() {
            @Override
            public void onJsFunctionCalled(String tag) {

            }
        }, "Android");
        webView.setWebViewClient(new WebViewClient() {
            /**
             * 防止加载网页时调起系统浏览器
             */
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onReceivedHttpAuthRequest(WebView webview,
                                                  com.tencent.smtt.export.external.interfaces.HttpAuthHandler httpAuthHandlerhost, String host,
                                                  String realm) {
                boolean flag = httpAuthHandlerhost.useHttpAuthUsernamePassword();
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view,
                                                              WebResourceRequest request) {

                Log.e("should", "request.getUrl().toString() is "
                        + request.getUrl().toString());

                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }

        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                appTitle.getmCenterTitle().setSingleLine(true);
                appTitle.getmCenterTitle().setEllipsize(TextUtils.TruncateAt.MARQUEE);
                appTitle.getmCenterTitle().setFocusable(true);
                appTitle.getmCenterTitle().setFocusableInTouchMode(true);
                appTitle.getmCenterTitle().setMaxEms(11);
                appTitle.setCenterTitle(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (!(newProgress == 100)) {
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                } else if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        url = getIntent().getStringExtra("htmlURL");
        webView.loadUrl(url);
        if (webView.getX5WebViewExtension() != null) {
            appTitle.setRightText("X5  Core:" + QbSdk.getTbsVersion(this.getContext()));
        } else {
            appTitle.setRightText("Sys Core:" + QbSdk.getTbsVersion(this.getContext()));
        }
    }


    /**
     * 改写物理按键——返回的逻辑
     **/
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();// 返回上一页面
                return true;
            } else {
                finish();// 退出
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.removeAllViews();
            webView.destroy();
        }
        super.onDestroy();
    }


}
