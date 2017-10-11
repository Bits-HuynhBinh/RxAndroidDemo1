package com.hnb.rxandroiddemo5;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Func1;
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

    public static final int REQUEST_READ_PHONE_STATE = 1011;

    private void showData(RealmResults<User> data)
    {
        Log.e("data", "daaaa");
        Log.e("Data1", users.size() + "");
    }

    public static GithubUser saveDB(GithubUser user)
    {
        Log.e("save db", user.email);
        return  user;
    }

    private static ABC UserToABC(GithubUser user, int id)
    {
        ABC abc = new ABC();
        abc.A = user.name;
        abc.B = user.avatar_url;

        Log.e("Map", id + "-" + abc.toString());

        return abc;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_PHONE_STATE:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO

                    final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

                    final String tmDevice, tmSerial, androidId;
                    tmDevice = "" + tm.getDeviceId();
                    tmSerial = "" + tm.getSimSerialNumber();
                    androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

                    Log.e("ID1", tmDevice);
                    Log.e("ID2", tmSerial);
                    Log.e("ID3", androidId);
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Person person = new Person();
        person.dog = new Dog();
        person.dog.person = person;
        person.dog.person.dog = person.dog;

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        } else {
            //TODO
        }



        final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        Log.e("ID1", tmDevice);
        Log.e("ID2", tmSerial);
        Log.e("ID3", androidId);


       /* for(int i = 0; i < 1; i++)
        {
            final int a = i;
            APIObservable
                    .getUsers()
                    .subscribeOn(Schedulers.newThread())
                    .map(user -> UserToABC(user, a))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(abc -> {Log.e("subscribe", a + "-" + abc.toString());});
        }*/

        /*context = this;
        initView();


        Subscription subscription = APIObservable.getUsers().map(users -> saveDB(users))
                .subscribe(user -> {Log.e("update UI",user.email);});


        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        subscription.unsubscribe();*/





       /* APIObservable.getUsers().map(new Func1<GithubUser, Object>()
        {
            @Override
            public Object call(GithubUser githubUser)
            {
                return null;
            }
        });*/

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
        //Observable click = RxView.clicks(button);
        //click.throttleWithTimeout(1000, TimeUnit.MILLISECONDS).subscribe(data -> Log.e("click", "click"));


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
