package com.hnb.rxandroiddemo5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import java.util.concurrent.atomic.AtomicInteger;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Huynh Binh PC on 6/24/2016.
 */
public class SQLBriteManager
{
    SqlMyHelper sqlMyHelper = null;
    BriteDatabase db = null;

    final AtomicInteger queries = new AtomicInteger();

    public SQLBriteManager(Context context)
    {
        sqlMyHelper = new SqlMyHelper(context);

        SqlBrite sqlBrite = SqlBrite.create();
        db = sqlBrite.wrapDatabaseHelper(sqlMyHelper, Schedulers.io());
    }

    public void printNumber()
    {
        Log.e("Number", queries.get() + "");
    }


    public void query()
    {
        Observable<SqlBrite.Query> users = db.createQuery(SqlMyHelper.FeedEntry.TABLE_NAME, "SELECT * FROM " + SqlMyHelper.FeedEntry.TABLE_NAME);
        users.subscribe(query -> {
            Cursor cursor = query.run();

            if (cursor.moveToFirst())
            {
                do
                {
                    String id = cursor.getString(cursor.getColumnIndex(SqlMyHelper.FeedEntry.COLUMN_NAME_ENTRY_ID));
                    String title = cursor.getString(cursor.getColumnIndex(SqlMyHelper.FeedEntry.COLUMN_NAME_TITLE));

                    Log.e("OK", id + "-" + title);
                }
                while (cursor.moveToNext());
            }
            cursor.close();

            queries.getAndIncrement();

        });

    }


    public void insert()
    {
        db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("1", "11111"));
        db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("2", "22222"));
        db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("3", "333333"));
    }

    public void insertWithTransaction()
    {
        BriteDatabase.Transaction transaction = db.newTransaction();

        try
        {
            db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("4", "4444"));
            db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("5", "5555"));
            db.insert(SqlMyHelper.FeedEntry.TABLE_NAME, createData("6", "6666"));
            transaction.markSuccessful();
        }
        catch (Exception ex)
        {

        }
        finally
        {
            transaction.end();
        }
    }


    public static ContentValues createData(String id, String title)
    {
        ContentValues values = new ContentValues();
        values.put(SqlMyHelper.FeedEntry.COLUMN_NAME_ENTRY_ID, id);
        values.put(SqlMyHelper.FeedEntry.COLUMN_NAME_TITLE, title);

        return values;

    }
}
