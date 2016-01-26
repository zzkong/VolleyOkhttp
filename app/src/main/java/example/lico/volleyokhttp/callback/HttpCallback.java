package example.lico.volleyokhttp.callback;


import example.lico.volleyokhttp.bean.HttpResponse;

/**
 * Created by zzk on 16/1/26.
 */
public interface HttpCallback {

    void ParserCompleteListener(HttpResponse response, Object object);

}
