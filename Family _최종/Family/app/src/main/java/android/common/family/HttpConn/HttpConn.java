package android.common.family.HttpConn;

import android.common.family.BoardParsing;
import android.common.family.Board_out;
import android.common.family.GalleryActivity;
import android.common.family.R;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

//HttpURLConnection사용할것
public class HttpConn extends AsyncTask<Void, Void, String> {
    private String urlPath;
    public static String result;
    private HttpType type;
    //String[] _ParsedData ;

    public HttpConn(String urlPath, HttpType type) {
        this.urlPath = urlPath;
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    @Override
    protected String doInBackground(Void... voids) {
        // TODO Auto-generated method stub
        StringBuilder output = new StringBuilder() ;
        try {
            URL _url = new URL(urlPath) ;
            Log.e("! 1","trying conn ") ;
            HttpURLConnection conn = (HttpURLConnection)_url.openConnection() ;
            if(conn !=null){
                conn.setConnectTimeout(5000) ;
                conn.setRequestMethod("GET");
                conn.setDoInput(true) ;
                conn.setDoOutput(true) ;
            }

            Log.e("! 2","trying conn ") ;
            int resCode = conn.getResponseCode() ;
            if(resCode==HttpURLConnection.HTTP_OK){
                Log.e("! 2.5","Http conn is OK") ;
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
                String line = null;
                Log.e("! 3","trying conn ") ;
                while(true){
                    line = reader.readLine();
                    if(line==null)
                        break;
                    output.append(line+"\n") ;
                }
                reader.close();
                conn.disconnect() ;
            }
            Log.e("! result",output.toString()) ;
            return result = output.toString() ;
            //HttpPost request = new HttpPost(urlPath);
                /*
                //전달할 인자들
                Vector<NameValuePair> nameValue = new Vector<NameValuePair>();
                nameValue.add(new BasicNameValuePair("id", ""));
                nameValue.add(new BasicNameValuePair("name", ""));
                nameValue.add(new BasicNameValuePair("score", ""));
                nameValue.add(new BasicNameValuePair("rival", ""));
                */

            //웹 접속 - utf-8 방식으로
            //HttpEntity enty = new UrlEncodedFormEntity(nameValue, HTTP.UTF_8) ;
            //request.setEntity(enty);

            //HttpClient client = new DefaultHttpClient();
            //HttpResponse res = client.execute(request);
            //웹 서버에서 값받기
            //HttpEntity entityResponse = res.getEntity();
            //InputStream im = entityResponse.getContent();
            //BufferedReader reader = new BufferedReader(new InputStreamReader(im, HTTP.UTF_8));

            //String total = "";
            //String tmp = "";
            //버퍼에있는거 전부 더해주기
            //readLine -> 파일내용을 줄 단위로 읽기
            //while ((tmp = reader.readLine()) != null) {
            //    if (tmp != null) {
            //       total += tmp;
            //    }
            //}
            //im.close();
            //결과창뿌려주기 - ui 변경시 에러
            //result.setText(total);
            //result = total;

            //return total;
        } catch (UnsupportedEncodingException e) {
            Log.e("HttpConn_Unsupported...", e.toString());
        } catch (IOException e) {
            Log.e("HttpConn_IOException", e.toString());
        }
        //오류시 null 반환
        return null;
    }

    //asyonTask 3번째 인자와 일치 매개변수값 -> doInBackground 리턴값이 전달됨
    //AsynoTask 는 preExcute - doInBackground - postExecute 순으로 자동으로 실행됩니다.
    //ui는 여기서 변경
    protected void onPostExecute(String value) {
        super.onPostExecute(value);
        if (this.type == HttpType.talk_input) {
            //ParsingList parsing = new ParsingList() ;
            //KakaoServiceListActivity.friendList.setText(parsing.getResult()) ;
        } else if (this.type == HttpType.talk_list) {
            TalkParsing _tp = new TalkParsing(result);
            TextView _tv = (TextView) android.common.family.TalkActivity.talk_list;
            _tv.setText(_tp.Parsing());
        } else if (this.type == HttpType.board_input) {
            Log.e("! success", "board_input");
        } else{

        }

    }

}
