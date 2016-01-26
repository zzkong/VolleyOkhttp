package example.lico.volleyokhttp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import example.lico.volleyokhttp.R;
import example.lico.volleyokhttp.bean.GankBean;
import example.lico.volleyokhttp.bean.HttpResponse;
import example.lico.volleyokhttp.bean.ResponseImagesListEntity;
import example.lico.volleyokhttp.callback.HttpCallback;
import example.lico.volleyokhttp.http.DataParser;
import example.lico.volleyokhttp.http.HttpManager;

/**
 * Created by zzk on 16/1/26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mGetBtn1, mGetBtn2, mPostBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetBtn1 = (Button) findViewById(R.id.get_request);
        mGetBtn2 = (Button) findViewById(R.id.get_request2);
        mPostBtn = (Button) findViewById(R.id.post_request);


        mGetBtn1.setOnClickListener(this);
        mGetBtn2.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.get_request:  //get请求1
                getData1();
                break;
            case R.id.get_request2: //get请求2
                getData2();
                break;
            case R.id.post_request: //post请求
              //  Map<String, String> map = new HashMap<>();
                break;
        }
    }

    private void getData1(){  //访问gank api数据
        String url = "http://gank.avosapps.com/api/data/Android/10/1";
        HttpManager.getCommData(url, GankBean.class, new HttpCallback() {
            @Override
            public void ParserCompleteListener(HttpResponse response, Object object) {
                if(response.code != 1){
                    Toast.makeText(MainActivity.this, response.message, Toast.LENGTH_SHORT).show();
                }else {
                    GankBean bean = (GankBean) object;
                    Toast.makeText(MainActivity.this, bean.results.get(2).who, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getData2(){  //访问百度美女图片数据
        String urls = "http://image.baidu.com/data/imgs?col=%E7%BE%8E%E5%A5%B3&tag=%E5%85%A8%E9%83%A8&pn=0&rn=20&from=1";
        HttpManager.getCommData(urls, ResponseImagesListEntity.class, new HttpCallback() {
            @Override
            public void ParserCompleteListener(HttpResponse response, Object object) {
                if(response.code != 1){
                    Toast.makeText(MainActivity.this, response.message, Toast.LENGTH_SHORT).show();
                }else {
                    ResponseImagesListEntity bean = (ResponseImagesListEntity) object;
                    Toast.makeText(MainActivity.this, bean.imgs.get(2).downloadUrl, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
