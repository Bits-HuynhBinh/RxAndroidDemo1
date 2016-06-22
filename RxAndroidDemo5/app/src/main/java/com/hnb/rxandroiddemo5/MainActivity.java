package com.hnb.rxandroiddemo5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity
{

    TextView textView;

    Button button;
    EditText editText;

    CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        RxTextView.textChanges(editText).map(data -> new StringBuilder(data).reverse().toString()).subscribe(finalString -> textView.setText(finalString));


        //button.setOnClickListener(v -> test2());


        Observable click = RxView.clicks(button).share();

        /*click.subscribe(view -> {
            DataSourcesObservable sources = new DataSourcesObservable();
            // Create our sequence for querying best available data
            Observable<Data> source = Observable.concat(sources.memory(), sources.disk(), sources.network()).first(data -> data != null && data.isUpToDate());

            // "Request" latest data once a second
            Observable.interval(1, TimeUnit.SECONDS).flatMap(aLong -> source).subscribe(data -> Log.e("Received: ", data.value));

            // Occasionally clear memory (as if app restarted) so that we must go to disk
            Observable.interval(3, TimeUnit.SECONDS).subscribe(aLong -> sources.clearDataInMemory());

        });*/

        /*click.subscribe(view -> {
            APIObservable.onErrorReturn().subscribe(data -> Log.e("data", data));
        });*/


        click.subscribe(view -> {
            APIObservable.retry().subscribe(data -> Log.e("data", data));
        });




        /*click.subscribe(view -> {
            APIObservable.getUsers().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(user -> {
                Log.e("user", user.login + user.avatar_url + user.created_at + user.email);
            });
        });*/


        /*click.subscribe(view -> {
            APIObservable.getUsersWithDetail().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(user -> {
                GithubUser githubUser = (GithubUser) user;
                Log.e("user", githubUser.login + githubUser.avatar_url + githubUser.created_at + githubUser.email);
            }, error -> {
                Log.e("ERROR11111", ((Exception) error).getMessage());
            });
        });*/


        /*click.subscribe(view -> {
            APIObservable.getUsers().subscribeOn(Schedulers.from(new MyExcutor())).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> Log.e("U", data.login));
        });*/

        /*click.subscribe(view -> {
            APIObservable.getUsers1().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> Log.e("Data", data));
        });*/

        /*click.subscribe(view -> {
            APIObservable.getUsers2().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> Log.e("Data", data.login));
        });*/


        /*click.subscribe(view -> {
            Observable<String> observable1 = APIObservable.getUsers();

            Observable<String> observable2 = APIObservable.forgotPassword();

            Observable.zip(observable1, observable2, (data1, data2) -> data2 + data1)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(data3 -> Log.e("data3", data3));

        });*/
    }

    public void initView()
    {
        textView = (TextView) findViewById(R.id.txtView);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edtData);
    }


    public void test1()
    {
        APIObservable.getGithubUserList(this, "https://api.github.com/users");
    }

    public void test2()
    {
        APIObservable.fetchDataFromGoogle("http://www.google.com").subscribe();
    }
}
