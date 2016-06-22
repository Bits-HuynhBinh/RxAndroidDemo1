package com.hnb.rxandroiddemo5;

/**
 * Simple data class, keeps track of when it was created
 * so that it knows when the its gone stale.
 */
public class Data
{

    private static final long ALIVE_TIME = 5 * 1000; // Data is stale after 5 seconds

    final String value;

    final long timestamp;

    public Data(String value)
    {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isUpToDate()
    {
        boolean check = false;
        if(System.currentTimeMillis() - timestamp < ALIVE_TIME)
        {
            check = true;
        }
        else
        {
            check = false;
        }

        return check;
    }
}
