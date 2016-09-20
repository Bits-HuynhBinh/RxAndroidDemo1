package com.hnb.rxandroiddemo5;

import android.content.Context;
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
import com.trello.rxlifecycle.RxLifecycle;

import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
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

    Button btnGet;

    Context context = null;

    Button btnInsert;

    CompositeSubscription compositeSubscription;
    static int mCount = 0;


    public static RealmResults<User> users;

    private RealmChangeListener dogListener;

    private void showData(RealmResults<User> data)
    {
        Log.e("data", "daaaa");
        Log.e("Data1", users.size() + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();

        /*Realm myRealm = Realm.getInstance(MyApplication.getConfig(this));
        users = myRealm.where(User.class).findAll();

        users.addChangeListener(new RealmChangeListener<RealmResults<User>>()
        {
            @Override
            public void onChange(RealmResults<User> element)
            {
                int size = element.size();
                Log.e("sds",size +"");
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //getData(MainActivity.this);
                insertToRealm(context);
            }
        });


        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                updateToRealm(context);
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                textView.setText(getDataFromRealm(context));
            }
        });*/


        //myRealm.where(User.class).findAll().asObservable();

        /*users.addChangeListener(new RealmChangeListener<RealmResults<User>>()
        {
            @Override
            public void onChange(RealmResults<User> element)
            {
                Log.e("Changed", "changed");
            }
        });*/


        //Observable<Realm> realmObservable = myRealm.asObservable();
        //Observable<RealmResults<User>> resultsObservable = users.asObservable();
        //resultsObservable.debounce(100, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> showData(data) );


        //RxTextView.textChanges(editText).map(data -> new StringBuilder(data).reverse().toString()).subscribe(finalString -> textView.setText(finalString));
        //button.setOnClickListener(v -> test2());

        //PermissionManager.requestEach(this);

        /*SQLBriteManager sqlBriteManager = new SQLBriteManager(this);
        Observable click = RxView.clicks(button).share();
        Observable btnInsertClicks = RxView.clicks(btnInsert);
        click.subscribe(view -> {
            sqlBriteManager.query();
        });

        btnInsertClicks.subscribe(view -> {
            sqlBriteManager.insertWithTransaction();
        });*/


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


        /*click.subscribe(view -> {
            APIObservable.retry().subscribe(data -> Log.e("data", data));
        });*/


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

       /* String [] test = new String[5];
        test[0] = "a";
        test[0] = "b"; test[0] = "c";
        test[0] = "d";
        test[0] = "e";*/
        //.flatMap(aLong -> source).subscribe(data -> Log.e("Received: ", data.value));


        // click throttle
        // https://camo.githubusercontent.com/995c301de2f566db10748042a5a67cc5d9ac45d9/687474703a2f2f692e696d6775722e636f6d2f484d47574e4f352e706e67
        Observable click = RxView.clicks(button);
        click.throttleWithTimeout(1000, TimeUnit.MILLISECONDS).subscribe(data -> Log.e("click", "click"));


    }

    public void initView()
    {
        textView = (TextView) findViewById(R.id.txtView);
        button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.edtData);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnGet = (Button) findViewById(R.id.btnGet);
    }


    public void test1()
    {
        APIObservable.getGithubUserList(this, "https://api.github.com/users");
    }

    public void test2()
    {
        APIObservable.fetchDataFromGoogle("http://www.google.com").subscribe();
    }


    public static void insertToRealm(Context context)
    {
        Realm realmbg = Realm.getInstance(MyApplication.getConfig(context));
        realmbg.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {
                User user = realm.createObject(User.class);
                user.setName("user 133333");
                user.setAge(1);
                //user.setSessionId(1);
            }
        });
    }

    public static void updateToRealm(Context context)
    {
        Realm realmbg = Realm.getInstance(MyApplication.getConfig(context));
        realmbg.executeTransaction(new Realm.Transaction()
        {
            @Override
            public void execute(Realm realm)
            {

                User user = new User();
                user.setName("user 1111");
                user.setAge(1);
                user.setSessionId(1);

                realm.copyToRealmOrUpdate(user);
            }
        });
    }


    public static String getDataFromRealm(Context context)
    {
        Realm realmbg = Realm.getInstance(MyApplication.getConfig(context));
        RealmResults<User> us = realmbg.where(User.class).findAll();

        StringBuilder stringBuilder = new StringBuilder();

        for (User u : us)
        {
            stringBuilder.append(u.getName() + "-" + u.getAge() + "-" + u.getSessionId());
            stringBuilder.append("-");
        }

        return stringBuilder.toString();
    }


    public static void getData(final Context context)
    {

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {
                Log.e("Thread", Thread.currentThread().getName());
                Realm realmbg = Realm.getInstance(MyApplication.getConfig(context));

                realmbg.beginTransaction();
                for (int i = 0; i < 100; i++)
                {
                    User dog = realmbg.createObject(User.class);
                    dog.setName("name" + i);
                    dog.setAge(i);

                }
                realmbg.commitTransaction();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
}
