package com.hnb.rxandroiddemo5;

/**
 * Created by Huynh Binh PC on 6/9/2016.
 */
public class Operation
{
    public static Integer runLongTimeOperation()
    {
        try
        {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1234;
    }
}
