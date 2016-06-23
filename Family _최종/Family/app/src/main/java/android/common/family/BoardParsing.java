package android.common.family;

/**
 * Created by 조디 on 2016-05-12.
 */
public class BoardParsing {

    private String _Data ;
    private String[] _ParsedData ;

    public BoardParsing(String _data){
        this._Data = _data ;
        this._ParsedData = this._Data.split("[|]") ;

    }

    public String[] Parsing(){
        String[] result = new String[this._ParsedData.length] ;
        int j = 0 ;
        for(int i=0 ; i<this._ParsedData.length ; i++){
            if(i%5==0)
                j++ ;
            result[j] = result[j] + this._ParsedData[i]+"|" ;

        }
        return result ;
    }

}
