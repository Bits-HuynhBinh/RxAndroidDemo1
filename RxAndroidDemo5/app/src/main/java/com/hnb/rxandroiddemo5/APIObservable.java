package com.hnb.rxandroiddemo5;

import android.content.Context;
import android.view.ViewTreeObserver;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Huynh Binh PC on 6/14/2016.
 */
public class APIObservable
{

    public static Observable<String> forgotPassword()
    {
        return Observable.create(subscriber -> {
            try
            {
                String data = API.forgotPassword();
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
            catch (Exception ex)
            {
                subscriber.onError(ex);
            }
        });
    }

    public static Observable fetchDataFromGoogle(String url)
    {
        return Observable.create(subscriber -> {
            try
            {
                String data = API.fetchData(url);
                subscriber.onNext(data);
                subscriber.onCompleted();
            }
            catch (Exception ex)
            {
                subscriber.onError(ex);
            }

        });
    }


    public static Observable<String> getUsersString()
    {
        return Observable.create(subscriber -> {
            try
            {
                String result = API.getUsers();
                subscriber.onNext(result);
                subscriber.onCompleted();
            }
            catch (Exception ex)
            {
                subscriber.onError(ex);
            }
        });
    }

    public static Observable<GithubUser> getUsers()
    {
        Observable.just(API.getUsers());

        return getUsersString().flatMap(data -> {

            Gson gson = new Gson();
            Type listType = new TypeToken<List<GithubUser>>()
            {
            }.getType();

            List<GithubUser> githubUsers = gson.fromJson(data, listType);

            Observable<GithubUser> abc = Observable.from(githubUsers);

            return abc;
        });
    }


    public static Observable<GithubUser> getUsersWithDetail()
    {
        Observable<GithubUser> observable = getUsers().map(githubUser -> {

            String data = API.getUserDetail(githubUser.login);
            Gson gson = new Gson();
            GithubUser githubUser1 = gson.fromJson(data, GithubUser.class);
            return githubUser1;

        });

        return observable;
    }

    // test
    public static void getGithubUserList(Context context, String url)
    {

        Observable<String> requestStream = Observable.just(url);

        /*requestStream.subscribe(url1 ->
        {
            Observable<String> responseStream = Observable.create(subscriber ->
            {
                StringRequest stringRequest = new StringRequest(
                        Request.Method.GET,
                        url,
                        data-> {subscriber.onNext(data);
                            subscriber.onCompleted();},
                        error -> subscriber.onError(error));

                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(stringRequest);
            });


            responseStream.subscribe(responseData -> System.out.println(responseData));

        });*/

        Observable responseStream = requestStream.flatMap(url1 ->


                Observable.create(subscriber -> {
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url1, data -> {
                        subscriber.onNext(data);
                        subscriber.onCompleted();
                    }, error -> subscriber.onError(error));

                    RequestQueue queue = Volley.newRequestQueue(context);
                    queue.add(stringRequest);
                })

        );

        responseStream.subscribe(data -> System.out.println(data));

    }

}
