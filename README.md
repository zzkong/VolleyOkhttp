1、用Okhttp作为Volley网络底层传输；
2、使用公共的泛型解析。具体看代码
3、Post方法未实现
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
