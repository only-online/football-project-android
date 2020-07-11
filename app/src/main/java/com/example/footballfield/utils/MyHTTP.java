package com.example.footballfield.utils;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class MyHTTP {


    private static final String BASE_URL = "https://footballfieldrent.kg/api";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, JsonHttpResponseHandler responseHandler) {
        client.setTimeout(60000);
        client.get(getAbsoluteUrl(url), responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.setTimeout(60000);
        Log.e("url", getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(Context context, String url, HttpEntity entity, JsonHttpResponseHandler responseHandler) {
        applySSL();
        client.setTimeout(60000);
        Log.e("url", url);
        client.post(context, url, entity, "application/json", responseHandler);
    }

    public static void post(Context context, HttpEntity entity, JsonHttpResponseHandler responseHandler) {
        applySSL();
        client.setTimeout(60000);
        Log.e("url", BASE_URL);
        client.post(context, BASE_URL, entity, "application/json", responseHandler);
    }

    public static void getWithUrl(String url, JsonHttpResponseHandler responseHandler) {
        client.setTimeout(60000);
        Log.e("url", url);
        client.get(url, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    private static void applySSL() {
//        try {
//            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//            trustStore.load(null, null);
//            MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
//            sf.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//            client.setSSLSocketFactory(sf);
//        } catch (Exception e) {
//        }
    }

    public static void postWithURL(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.setTimeout(60000);
        client.post(url, params, responseHandler);
    }

    public static void post(Context mContext, StringEntity entity, String url, JsonHttpResponseHandler responseHandler) {
        applySSL();
        client.setTimeout(60000);
        Log.e("url", url);
        client.post(mContext, url, entity, "application/json", responseHandler);
    }
}
