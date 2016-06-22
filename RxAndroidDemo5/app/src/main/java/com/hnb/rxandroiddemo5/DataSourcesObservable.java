package com.hnb.rxandroiddemo5;

import android.util.Log;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Simulates three different sources - one from memory, one from disk,
 * and one from network. In reality, they're all in-memory, but let's
 * play pretend.
 * <p>
 * Observable.create() is used so that we always return the latest data
 * to the subscriber; if you use just() it will only return the data from
 * a certain point in time.
 */
public class DataSourcesObservable
{

    // Memory cache of data
    private Data memory = null;

    // What's currently "written" on disk
    private Data disk = null;

    // Each "network" response is different
    private int requestNumber = 0;

    // In order to simulate memory being cleared, but data still on disk
    public void clearDataInMemory()
    {
        System.out.println("Wiping memory...");
        memory = null;
    }

    public Observable<Data> memory()
    {
        Observable<Data> observable = Observable.create(subscriber -> {
            subscriber.onNext(memory);
            subscriber.onCompleted();
        });

        return observable.compose(logSource("MEMORY"));
    }

    public Observable<Data> disk()
    {
        Observable<Data> observable = Observable.create(subscriber -> {
            subscriber.onNext(disk);
            subscriber.onCompleted();
        });

        // Cache disk responses in memory
        return observable.doOnNext(data -> cacheToMemory(data)).compose(logSource("DISK"));
    }

    public Observable<Data> network()
    {
        Observable<Data> observable = Observable.create(subscriber -> {
            requestNumber++;
            subscriber.onNext(new Data("Server Response #" + requestNumber));
            subscriber.onCompleted();
        });

        // Save network responses to disk and cache in memory
        return observable.doOnNext(data -> {
            cacheToDisk(data);
            cacheToMemory(data);
        }).compose(logSource("NETWORK"));
    }

    // Simple logging to let us know what each source is returning
    Observable.Transformer<Data, Data> logSource(final String source)
    {
        return dataObservable -> dataObservable.doOnNext(data -> {
            if (data == null)
            {
                Log.e(source, " does not have any data.");
            }
            else if (!data.isUpToDate())
            {
                Log.e(source, " has stale data.");
            }
            else
            {
                Log.e(source, " has the data you are looking for!");
            }
        });
    }

    private void cacheToDisk(Data data)
    {
        this.disk = data;
    }

    private void cacheToMemory(Data data)
    {
        this.memory = data;
    }
}
