package android.common.family;

import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.TimerTask;

public class BoardInputActivity extends FragmentActivity {
    Button but_board,but_calendar,but_home,but_set,but_talk;
    private BackPressCloseSystem backPressCloseSystem;
    String ip = "168.188.124.215" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(Window.FEATURE_NO_TITLE, Window.FEATURE_NO_TITLE);
        setContentView(R.layout.board_input);


    }

    public void confirmBt(View view){
        EditText title = (EditText)findViewById(R.id.title) ;
        EditText writer = (EditText)findViewById(R.id.writer) ;
        EditText content = (EditText)findViewById(R.id.content) ;

        HttpConn httpconn = new HttpConn("http://"+ip+"/family_board_input.php/?title="+title.getText().toString()+"&content="+content.getText().toString()+"&writer="+writer.getText().toString(), HttpType.board_input) ;
        httpconn.execute() ;
        finish() ;
    }

}
