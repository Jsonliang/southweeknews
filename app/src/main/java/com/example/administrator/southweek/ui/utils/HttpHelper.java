package com.example.administrator.southweek.ui.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/19.
 * 访问网络工具类
 */
public class HttpHelper {
    private OkHttpClient mHttpClient;
    private static HttpHelper helper = null;
    private Handler mainHandler;

    private HttpHelper() {
        mHttpClient = new OkHttpClient();
        /*builder mode set connected timeout
        * */
        /*mHttpClient =new OkHttpClient.Builder().
                connectTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000,TimeUnit.MILLISECONDS).build();*/
        mainHandler = new Handler(Looper.getMainLooper());//主线程的 Looper
    }

    //单例模式
    public static HttpHelper getInstance() {
        if (helper == null) {
            synchronized (HttpHelper.class) {
                if (helper == null) {
                    helper = new HttpHelper();
                }
            }
        }
        return helper;
    }

    /**
     * get类型访问网络
     */
    public void requestByGet(String url, final CallBack callBack) {
        if(url == null) return ;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        mHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
               mainHandler.post(new Runnable() {
                   @Override
                   public void run() {
                       callBack.onFailure(e);
                   }
               });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if(callBack == null) return ;
                //图片回调，在主线程可直接使用
                if (callBack instanceof BitmapCallBack) {
                    mainHandler.post(new Runnable() {
                        Bitmap bitmap = BitmapFactory.decodeStream
                                (response.body().byteStream());
                        @Override
                        //主线程
                        public void run() {
                            callBack.onResult(bitmap);
                        }
                    });
                    //字节数组回调，在主线程可直接使用
                } else if (callBack instanceof ByteCallBack) {
                    final byte[] bytes = response.body().bytes() ;
                    mainHandler.post(new Runnable() {
                        @Override
                        //主线程
                        public void run() {
                                callBack.onResult(bytes);
                        }
                    });
                }else if(callBack instanceof StringCallBack){
                    final String returnData= response.body().string();
                    mainHandler.post(new Runnable() {
                        @Override
                        //主线程
                        public void run() {
                            callBack.onResult(returnData);
                        }
                    });
                } else {
                    //在子线程
                    callBack.onResult(response.body().byteStream());
                }
            }
        });

    }

    /**
     * 返回流的接口
     */
    public interface BitmapCallBack extends CallBack {
        @Override
        public void onFailure(Exception e);
        @Override
        public void onResult(Object bitmap);
    }

    /**
     * 字符串接口
     */
    public interface StringCallBack extends CallBack{
        @Override
        public void onFailure(Exception e);
        @Override
        public void onResult(Object string);
    }

    /**
     * 字节数组数组的接口
     */
    public interface ByteCallBack extends CallBack {
        @Override
        public void onFailure(Exception e);
        @Override
        public void onResult(Object bytes);
    }

    /**
     * 返回流的接口
     */
    public interface CallBack {
        public void onFailure(Exception e);
        public void onResult(Object inputStream);
    }
}
