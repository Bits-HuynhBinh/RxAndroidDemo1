package com.hnb.rxandroiddemo5;

import java.util.concurrent.Executor;

/**
 * Created by Huynh Binh PC on 6/9/2016.
 */
public class MyExcutor implements Executor
{

    @Override
    public void execute(Runnable runnable)
    {
        new Thread(runnable).start();
    }

}
