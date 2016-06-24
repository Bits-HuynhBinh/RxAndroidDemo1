package com.hnb.rxandroiddemo5;

import android.Manifest;
import android.content.Context;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.Observable;

/**
 * Created by Huynh Binh PC on 6/23/2016.
 */
public class PermissionManager
{
    public static void request(Context context)
    {
        RxPermissions.getInstance(context).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(granted -> {
            if (granted)
            {
                // Always true pre-M
                // I can control the camera now
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
            }
            else
            {
                // Oups permission denied
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void requestEach(Context context)
    {
        RxPermissions.getInstance(context).requestEach(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(permission -> {
            if (permission.granted)
            {
                // Always true pre-M
                // I can control the camera now
                Toast.makeText(context, permission.name, Toast.LENGTH_SHORT).show();
            }
            else
            {
                // Oups permission denied
                Toast.makeText(context, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static Observable.Transformer ensure(Context context)
    {
        return RxPermissions.getInstance(context).ensure(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA);
    }

}
