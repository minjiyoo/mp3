package android.common.family;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;

public class WriteBoardActivity extends Activity {

    Button but_board,but_calendar,but_home,but_set,but_talk;
    private BackPressCloseSystem backPressCloseSystem;
    public static ScrollView boardScroll ;
    public static View boardView ;
    public static View titleView ;
    public static View writerView ;
    public static String[] strs ;
    public static TextView ListView ;
    public static android.widget.ListView listView ;

    private WebView webView;

    private ValueCallback<Uri> filePathCallbackNormal;
    private ValueCallback<Uri[]> filePathCallbackLollipop;
    private final static int FILECHOOSER_NORMAL_REQ_CODE = 1;
    private final static int FILECHOOSER_LOLLIPOP_REQ_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_write_board);




        webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient() {
            // For Android < 3.0
            public void openFileChooser( ValueCallback<Uri> uploadMsg) {
                Log.d("WriteBoardActivity", "3.0 <");
                openFileChooser(uploadMsg, "");
            }
            // For Android 3.0+
            public void openFileChooser( ValueCallback<Uri> uploadMsg, String acceptType) {
                Log.d("WriteBoardActivity", "3.0+");
                filePathCallbackNormal = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_NORMAL_REQ_CODE);
            }
            // For Android 4.1+
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                Log.d("WriteBoardActivity", "4.1+");
                openFileChooser(uploadMsg, acceptType);
            }

            // For Android 5.0+
            public boolean onShowFileChooser(
                    WebView webView, ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {
                Log.d("WriteBoardActivity", "5.0+");
                if (filePathCallbackLollipop != null) {
                    filePathCallbackLollipop.onReceiveValue(null);
                    filePathCallbackLollipop = null;
                }
                filePathCallbackLollipop = filePathCallback;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_LOLLIPOP_REQ_CODE);

                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.0.24:8080/Board_Blog__/write.jsp");
        webView.setVerticalScrollBarEnabled(true);

        but_board = (Button)findViewById(R.id.board);
        but_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteBoardActivity.this, MiniWebActivity.class));
            }
        });

        but_calendar = (Button)findViewById(R.id.calendar);
        but_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteBoardActivity.this, CalendarActivity.class));
            }
        });

        but_set = (Button)findViewById(R.id.setting);
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteBoardActivity.this, SettingActivity.class));
            }
        });

        but_talk = (Button)findViewById(R.id.talk);
        but_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteBoardActivity.this,TalkActivity.class));
            }
        });

        but_home = (Button)findViewById(R.id.home);
        but_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WriteBoardActivity.this,MainActivity.class));
            }
        });

        backPressCloseSystem = new BackPressCloseSystem(this);

    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////

    public class myWebClient extends WebViewClient  {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }

}//myWebClient
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    //flipscreen not loading again
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILECHOOSER_NORMAL_REQ_CODE) {
            if (filePathCallbackNormal == null) return ;
            Uri result = (data == null || resultCode != RESULT_OK) ? null : data.getData();
            filePathCallbackNormal.onReceiveValue(result);
            filePathCallbackNormal = null;
        } else if (requestCode == FILECHOOSER_LOLLIPOP_REQ_CODE) {
            if (filePathCallbackLollipop == null) return ;
            filePathCallbackLollipop.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
            filePathCallbackLollipop = null;
        }
    }


    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }

}
