package com.hnb.rxandroiddemo5;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import javax.net.ssl.HttpsURLConnection;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Huynh Binh PC on 6/9/2016.
 */
public class API
{

    public static String fetchData1(String urlStr)
    {
        throw new RuntimeException();
    }

    public static String fetchData(String urlStr) throws Exception
    {
        return HttpUtils.httpGet(urlStr);
    }

    //get
    public static String getUsers() throws Exception
    {
        String strUrl = "https://api.github.com/users";
        return HttpUtils.httpGet(strUrl);
    }

    public static String getUsers1()
    {
        String strUrl = "https://api.github.com/users";
        return HttpUtils.httpGet1(strUrl);
    }


    public static String getUserDetail(String login) throws Exception
    {
        String baseUrl = "https://api.github.com/users";
        String requestUrl = baseUrl + "/" + login;

        return HttpUtils.httpGet(requestUrl);
    }


    //post
    public static String forgotPassword()
    {
        //url
        String strUrl = "http://dev.getdibly.com/WebService/V1/forgotPassword";
        //params
        HashMap<String, String> map = new HashMap<>();
        map.put("email", "loi@gmail.com");
        //request
        return HttpUtils.httpPost(strUrl, map);
    }

}
