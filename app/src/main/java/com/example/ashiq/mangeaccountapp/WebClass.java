package com.example.ashiq.mangeaccountapp;

/**
 * Created by Ashiq on 9/6/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebClass extends Activity{
    String website;
    WebView mywebview;
    Button backButton,mainButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webveiw);
        backButton=(Button)findViewById(R.id.backButton);
        mainButton=(Button) findViewById(R.id.mainButon);
        mywebview=(WebView)findViewById(R.id.myweb);
        Bundle bundle=getIntent().getExtras();
        website=bundle.getString("Website");
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.loadUrl("http://"+website);


    }



    void onBack(View view)
    {
        if ( mywebview.canGoBack()) {
            mywebview.goBack();
        }else
        {
            onMainmenu(view);
        }
    }
    void onBack()
    {
        if ( mywebview.canGoBack()) {
            mywebview.goBack();
        }else
        {
            onMainmenu();
        }
    }
    void onMainmenu( View view)
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
    void onMainmenu()
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}

