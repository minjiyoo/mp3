package android.common.family;

import android.app.Activity;


import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.TimerTask;

public class Board_out extends Activity {
    private BackPressCloseSystem backPressCloseSystem;
    String ip = "168.188.124.215" ;
    public static LinearLayout _Llayout ;

    public Board_out(String title, String writer, String content){
        this.setTitle(title);
        this.setContent(content);
        this.setWriter(writer);

        _Llayout = (LinearLayout)findViewById(R.id.addBoard) ;
    }

    public Board_out(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_output);

    }

    public void setTitle(String str){
        TextView tv = (TextView)findViewById(R.id.title) ;
        tv.setText(str) ;
    }
    public void setWriter(String str){
        TextView tv = (TextView)findViewById(R.id.writer) ;
        tv.setText(str) ;
    }
    public void setContent(String str){
        TextView tv = (TextView)findViewById(R.id.content_textbox) ;
        tv.setText(str) ;
    }


}
