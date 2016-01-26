package example.lico.volleyokhttp.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import example.lico.volleyokhttp.bean.HttpResponse;
import example.lico.volleyokhttp.callback.HttpCallback;

/**
 * Created by zzk on 16/1/26.
 */
public class DataParser {

    public static void parserCommData(String jsonStr, Class Bean, HttpCallback callback){
        try {
            Gson gson = new GsonBuilder().serializeNulls().create();
            JSONObject jsonObject = new JSONObject(jsonStr);
            Object object = gson.fromJson(jsonObject.toString(), Bean);
            HttpResponse response = new HttpResponse(1, "访问成功");
            callback.ParserCompleteListener(response, object);
        }catch (Exception e){
            HttpResponse response = new HttpResponse(0, "解析数据异常");
            callback.ParserCompleteListener(response, null);
        }
    }

    public static String getImagesListUrl(String category, int pageNum) {
        StringBuffer sb = new StringBuffer();
        sb.append("http://image.baidu.com/data/imgs");
        sb.append("?col=");
        try {
            sb.append(URLEncoder.encode(category, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&tag=");
        try {
            sb.append(URLEncoder.encode("全部", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sb.append("&pn=");
        sb.append(pageNum * 20);
        sb.append("&rn=");
        sb.append(20);
        sb.append("&from=1");
        return sb.toString();
    }
}
