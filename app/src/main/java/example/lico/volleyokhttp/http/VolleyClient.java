package example.lico.volleyokhttp.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import example.lico.volleyokhttp.app.EApplication;
import example.lico.volleyokhttp.bean.HttpResponse;
import example.lico.volleyokhttp.callback.HttpCallback;

/**
 * Created by zzk on 16/1/26.
 */
public class VolleyClient {

    /**
     * Get方式访问的StringRequest请求
     */
    public static void getRequest(String url, final HttpCallback listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {  //响应成功
                Log.e("访问成功", "" +s);
                HttpResponse response = new HttpResponse(1, "访问成功");
                listener.ParserCompleteListener(response, s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //响应失败
                Log.e("访问成功", "" + error);
                HttpResponse response = new HttpResponse(0, "访问失败");
                listener.ParserCompleteListener(response, null);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {  //添加请求头  如不需要可以去掉
                HashMap headers = new HashMap();
                headers.put("AuthenticationKey", "");
                headers.put("platform", "");
                headers.put("version", "");
                return headers;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1, 1.0f)); //设置超时时间
        EApplication.addRequest(stringRequest, null);
    }

    /**
     * POST方式访问的StringRequest请求
     */
    public static void postRequest(String url, final Map<String, String> map, final HttpCallback listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {   //响应成功
                HttpResponse response = new HttpResponse(1, "访问成功");
                listener.ParserCompleteListener(response, s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //响应失败
                HttpResponse response = new HttpResponse(0, "访问失败");
                listener.ParserCompleteListener(response, null);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {   //添加参数
                Log.e("Http:", "打印下参数:" + map.toString());
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {     //添加头文件 如不需要可以去掉
                HashMap headers = new HashMap();
                headers.put("AuthenticationKey", "");
                headers.put("platform", "");
                headers.put("version", "");
                return headers;
            }
        };
        EApplication.addRequest(stringRequest, "");
    }
}
