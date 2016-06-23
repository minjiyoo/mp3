package android.common.family.HttpConn;

/**
 * Created by 조디 on 2016-05-12.
 */
public class TalkParsing {

    private String _Data ;
    private String[] _ParsedData ;

    TalkParsing(String _data){
        this._Data = _data ;
        this._ParsedData = this._Data.split("[|]") ;

    }

    protected String Parsing(){
        String result = "" ;
        for(int i=1 ; i<this._ParsedData.length ; i+=4){
            result = result + "\n" + this._ParsedData[i+1] + " : " +this._ParsedData[i];
            if(this._ParsedData[i+1]==null)
                break ;
        }

        return result ;
    }

}
