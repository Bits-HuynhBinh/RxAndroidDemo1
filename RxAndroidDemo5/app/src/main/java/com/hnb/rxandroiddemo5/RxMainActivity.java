package com.hnb.rxandroiddemo5;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.trello.rxlifecycle.ActivityEvent;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func0;

/**
 * Created by Huynh Binh PC on 6/23/2016.
 */
public class RxMainActivity extends RxAppCompatActivity
{

    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_rx);
        initView();

        Observable clicks = RxView.clicks(btnStart);
        clicks.subscribe(view -> {
            startRxLifeCycle();
        });


    }

    public void startRxLifeCycle()
    {
        Observable.interval(1, TimeUnit.SECONDS).compose(bindUntilEvent(ActivityEvent.PAUSE)).subscribe(aLong -> Log.e("Data", aLong.toString()));
    }

    private void initView()
    {
        btnStart = (Button) findViewById(R.id.button);

    }
}
