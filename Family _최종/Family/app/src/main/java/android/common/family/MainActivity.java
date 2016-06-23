package android.common.family;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

public class MainActivity extends FragmentActivity {
    Button but_board,but_calendar,but_home,but_set,but_talk;
    private BackPressCloseSystem backPressCloseSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, Loading.class));

        but_board = (Button)findViewById(R.id.board);
        but_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MiniWebActivity.class));
            }
        });

        but_calendar = (Button)findViewById(R.id.calendar);
        but_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        but_set = (Button)findViewById(R.id.setting);
        but_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

        but_talk = (Button)findViewById(R.id.talk);
        but_talk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TalkActivity.class));
            }
        });

        but_home = (Button)findViewById(R.id.home);
        but_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        backPressCloseSystem = new BackPressCloseSystem(this);

        SharedPreferences pref ;
        pref = getSharedPreferences("isFirst", 0);
        pref.getBoolean("isFirst", false) ;

        if(pref.getBoolean("isFirst",false) == false){
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("isFirst", true);
            edit.commit();

            // 닉네임 입력 다이얼로그
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("닉네임 설정");
            alert.setMessage("닉네임을 입력하세요");

            final EditText name = new EditText(this);
            alert.setView(name);

            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String username = name.getText().toString();
                    SharedPreferences pref = getSharedPreferences("name", 0);
                    SharedPreferences.Editor edit = pref.edit();
                    edit = pref.edit();
                    edit.putString("name",  username);
                    edit.commit();
                }
            });

            alert.show();

            PopupWindow.OnDismissListener _listener ;
        }

        else{
        }

    }

    @Override
    public void onBackPressed() {
        backPressCloseSystem.onBackPressed();
    }
}
