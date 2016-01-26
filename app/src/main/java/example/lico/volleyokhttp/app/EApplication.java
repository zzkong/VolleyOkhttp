package example.lico.volleyokhttp.app;

import android.app.Application;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;

import example.lico.volleyokhttp.http.OkHttpStack;

/**
 * Created by zzk on 16/1/26.
 */
public class EApplication extends Application {

    public static final int OUT_TIME = 10000;
    public static final int TIMES_OF_RETRY = 1;

    private static EApplication instance = null;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static EApplication getInstance() {
        if (instance == null) {
            synchronized (EApplication.class) {
                if (instance == null) {
                    instance = new EApplication();
                }
            }
        }
        return instance;
    }

    public RequestQueue getVolleyRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(this, new OkHttpStack());
        }
        return mRequestQueue;
    }

    private static void addRequest(Request<?> request){
        getInstance().getVolleyRequestQueue().add(request);
    }

    public static void addRequest(Request<?> request, String tag){
        if(tag != null){
            request.setTag(tag);
        }
        //给每个请求重设超时 重试次数
        request.setRetryPolicy(new DefaultRetryPolicy(
                OUT_TIME,
                TIMES_OF_RETRY,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addRequest(request);
    }

    public static void cancelAllRequests(String tag){
        if(getInstance().getVolleyRequestQueue() != null){
            getInstance().getVolleyRequestQueue().cancelAll(tag);
        }
    }

    public static void cancelAllRequests(){
        if(getInstance().getVolleyRequestQueue()!=null){
            getInstance().getVolleyRequestQueue().cancelAll(instance);
        }
    }
}
