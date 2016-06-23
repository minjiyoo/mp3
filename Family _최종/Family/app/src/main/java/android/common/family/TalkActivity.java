package android.common.family;

import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class TalkActivity extends FragmentActivity {

    Button but_board,but_calendar,but_home,but_set,but_talk;
    private BackPressCloseSystem backPressCloseSystem;

    Socket client;
    String ip = "168.188.124.215";
    int port = 8000;

    public static View talk_list ;

    InputMethodManager imm;
    ScrollView ttl;

    TextView textView ;
    EditText editText ;
    ImageButton btn_send ;
    Thread thread ;
    clientThread clientThread ;
    Handler handler ;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_talk);

        imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        ttl = (ScrollView)findViewById(R.id.scrollView);

        pref = getSharedPreferences("name", 0);
        pref.getString("name","") ;

        but_board = (Button)findViewById(R.id.board);
        but_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TalkActivity.this, MiniWebActivity.class));
            }
        });

        but_calendar = (Button)findViewById(R.id.calendar);
        but_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TalkActivity.this, CalendarActivity.class));
            }
        });

        but_set = (Button)findViewById(R.id.setting);
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TalkActivity.this, SettingActivity.class));
            }
        });

        but_talk = (Button)findViewById(R.id.talk);
        but_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TalkActivity.this, TalkActivity.class));

            }
        });

        but_home = (Button)findViewById(R.id.home);
        but_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TalkActivity.this, MainActivity.class));
            }
        });
        backPressCloseSystem = new BackPressCloseSystem(this);

        //ipm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //ipm.hideSoftInputFromWindow(가져갈 화면.getWindowToken(), 0);
        //LinearLayout lin = (LinearLayout) findViewById(R.id.linearLayout) ;
        //lin.setOnTouchListener(new view.OnTouchListener(){
        //   ipm.toggleSoftInput( 0, 0 );
        //});

        talk_list = (TextView)findViewById(R.id.chatting) ;
        connect() ;

        textView = (TextView)findViewById(R.id.chatting);
        editText = (EditText)findViewById(R.id.message);
        btn_send = (ImageButton)findViewById(R.id.sendBt) ;

        handler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
                textView.append(bundle.getString("msg")+"\n");
            }
        };


        TimerTask task = new TimerTask(){
            @Override
            public void run() {
                HttpConn httpconn = new HttpConn("http://"+ip+"/family_talk_list.php/", HttpType.talk_list) ;
                httpconn.execute() ;
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, 1500);

    }

    public void linearOnClick(View v) {
        imm.hideSoftInputFromWindow(ttl.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }

    public void send_bt(View v){
        clientThread.send(pref.getString("name","")+"|"+editText.getText().toString());
        textView.append(pref.getString("name","")+" : "+editText.getText().toString()+"\n");
        ScrollView scrv = (ScrollView)findViewById(R.id.scrollView) ;
        scrv.fullScroll(ScrollView.FOCUS_DOWN);
        editText.setText("");
    }

    public void connect(){

        thread = new Thread(){
            public void run() {
                super.run();
                try {
                    client = new Socket(ip, port);

                    clientThread = new clientThread(client, handler);
                    clientThread.start();
                    if(client == null)
                        Log.e("Error : 0 | ", "client is null .") ;
                    else
                        Log.e("Success : client | ", client.getLocalAddress().toString()) ;

                } catch (UnknownHostException e) {
                    Log.e("Error : 1 | ", e.getMessage()) ;
                    e.printStackTrace();
                } catch (IOException e) {
                    Log.e("Error : 2 | ", e.getMessage()) ;
                    e.printStackTrace();
                }

            }
        };

        thread.start();
    }


}
