package android.common.family;

import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GalleryActivity extends FragmentActivity {
    Button but_board,but_calendar,but_home,but_set,but_talk;
    private BackPressCloseSystem backPressCloseSystem;
    String ip = "168.188.124.215" ;
    public static ScrollView boardScroll ;
    public static View boardView ;
    public static View titleView ;
    public static View writerView ;
    public static String[] strs ;
    public static TextView ListView ;
    public static ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_gallery);

        but_board = (Button)findViewById(R.id.board);
        but_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, GalleryActivity.class));
            }
        });

        but_calendar = (Button)findViewById(R.id.calendar);
        but_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, CalendarActivity.class));
            }
        });

        but_set = (Button)findViewById(R.id.setting);
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, SettingActivity.class));
            }
        });

        but_talk = (Button)findViewById(R.id.talk);
        but_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, TalkActivity.class));
            }
        });

        but_home = (Button)findViewById(R.id.home);
        but_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GalleryActivity.this, MainActivity.class));
            }
        });


        backPressCloseSystem = new BackPressCloseSystem(this) ;

        boardScroll = (ScrollView)findViewById(R.id.boardScroll) ;
        ListView = (TextView)findViewById(R.id.ListView) ;

        TimerTask adTast = new TimerTask() {
            public void run() {
                HttpConn httpconn = new HttpConn("http://"+ip+"/family_board_list.php", HttpType.board_list) ;
                httpconn.execute() ;
                            //key title writer content reply
            }
        };
        Timer timer = new Timer();
        timer.schedule(adTast, 0, 15000);

    }
    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }

    public void board_input (View v){
        startActivity(new Intent(GalleryActivity.this, BoardInputActivity.class));
    }


}
