package com.hnb.rxandroiddemo5;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import io.realm.RealmConfiguration;

/**
 * Created by Huynh Binh PC on 6/9/2016.
 */
public class MyApplication extends Application
{
    public static RequestQueue requestQueue;

    public static RealmConfiguration config;

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    public RequestQueue getRequestQueue(Context context)
    {

        if (requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    public static RealmConfiguration getConfig(Context context)
    {
        if(config == null)
        {
            config = new RealmConfiguration.Builder(context).build();
        }
        return config;
    }
}
