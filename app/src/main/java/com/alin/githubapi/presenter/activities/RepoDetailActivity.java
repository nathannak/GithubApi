package com.alin.githubapi.presenter.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alin.githubapi.R;
import com.alin.githubapi.util.UiUtils;

import androidx.appcompat.app.AppCompatActivity;

public class RepoDetailActivity extends AppCompatActivity
{

    // @Override
    // protected void onCreate(Bundle savedInstanceState)
    // {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_repo_detail);
    // }

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                UiUtils.showDialog(RepoDetailActivity.this, getString(R.string.please_wait_msg),getString(R.string.fetching_repo_msg));
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl)
            {
                UiUtils.hideProgress();
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                UiUtils.hideProgress();
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                UiUtils.hideProgress();
            }
        });

        webView.loadUrl(getIntent().getStringExtra("repoUrl"));
        setContentView(webView);

    }
}
