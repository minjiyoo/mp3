package android.common.family;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import android.common.family.HttpConn.HttpConn;
import android.common.family.HttpConn.HttpType;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class clientThread extends Thread {

    BufferedReader bufferR;
    static BufferedWriter bufferW;
    Socket client;
    Handler handler;
    String ip = "168.188.124.215";

    public clientThread(Socket client, Handler handler) {
        this.handler = handler;
        try {
            this.client = client;
            //연결된 소켓으로부터 대화를 나눌 스트림을 얻음
            bufferR = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bufferW = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //보내기
    public static void send(String data) {
        System.out.println("전송");
        try {

            System.out.println("data:" + data);
            bufferW.write(data + "\n");
            bufferW.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //받기
    public String listen() {
        String msg = null;
        try {
            while (true) {
                msg = bufferR.readLine();
                Message m = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("msg", msg);
                m.setData(bundle);
                handler.sendMessage(m);
                if(msg.equals("new")) {
                    HttpConn httpconn = new HttpConn("http://" + ip + "/family_talk_list.php/", HttpType.talk_list);
                    httpconn.execute();
                }
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }

    public void run() {
        super.run();
        listen();

    }

}

