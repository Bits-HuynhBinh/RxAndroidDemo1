package com.hnb.rxandroiddemo5;

/**
 * Created by Huynh Binh PC on 6/9/2016.
 */
public class Tasks
{
    public interface DataCallback
    {
        void onSuccess(Integer data);
        void onError(Exception ex);
    }


    public Runnable runLongOperationWithThread(DataCallback dataCallback)
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try
                {
                    Integer integer = Operation.runLongTimeOperation();
                    dataCallback.onSuccess(integer);
                }
                catch (Exception ex)
                {
                    dataCallback.onError(ex);
                }
            }
        };
       return runnable;
    }

}
