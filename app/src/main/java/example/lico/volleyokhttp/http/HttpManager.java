package example.lico.volleyokhttp.http;

import android.util.Log;

import example.lico.volleyokhttp.bean.HttpResponse;
import example.lico.volleyokhttp.callback.HttpCallback;

/**
 * Created by zzk on 16/1/26.
 */
public class HttpManager {

    public static void getCommData(String url, final Class bean, final HttpCallback callback){
        VolleyClient.getRequest(url, new HttpCallback(){

            @Override
            public void ParserCompleteListener(HttpResponse response, Object object) {
                if(response.code == 1){
                    DataParser.parserCommData(object.toString(), bean, callback);
                }else {
                    callback.ParserCompleteListener(response, null);
                }
            }
        });
    }
}
