package android.common.family;

import android.app.ActionBar;
import android.app.Activity;
import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MiniWebActivity extends Activity {
    Button but_board,but_calendar,but_home,but_set,but_talk,write_button;
    private BackPressCloseSystem backPressCloseSystem;
    public static ScrollView boardScroll ;
    public static View boardView ;
    public static View titleView ;
    public static View writerView ;
    public static String[] strs ;
    public static TextView ListView ;
    public static android.widget.ListView listView ;

    private WebView mWebview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mini_web);

        mWebview = (WebView)findViewById(R.id.webView);
        mWebview.loadUrl("http://192.168.0.24:8080/Board_Blog__/list.jsp");
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebViewClient(new WebViewClientClass());
        mWebview.setVerticalScrollBarEnabled(true);




/*

        WebSettings settings =   mWebview.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
*/


        write_button = (Button)findViewById(R.id.writeButton);
        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this, WriteBoardActivity.class));
            }
        });

        but_board = (Button)findViewById(R.id.board);
        but_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this, MiniWebActivity.class));
            }
        });

        but_calendar = (Button)findViewById(R.id.calendar);
        but_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this, CalendarActivity.class));
            }
        });

        but_set = (Button)findViewById(R.id.setting);
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this, SettingActivity.class));
            }
        });

        but_talk = (Button)findViewById(R.id.talk);
        but_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this,TalkActivity.class));
            }
        });

        but_home = (Button)findViewById(R.id.home);
        but_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MiniWebActivity.this,MainActivity.class));
            }
        });

        backPressCloseSystem = new BackPressCloseSystem(this);



    }



    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
    
/*
    @Override
    public boolean onKeyDown(int keyCode, keyEvent event) {
        if ((keyCode == keyEvent.KEYCODE_BACK) && mWebview.canGoBack()) {
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
*/
}
