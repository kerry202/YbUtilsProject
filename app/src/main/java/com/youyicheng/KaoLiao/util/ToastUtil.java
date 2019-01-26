package com.youyicheng.KaoLiao.util;

import android.app.Activity;
import android.view.Gravity;
import android.widget.Toast;

public class ToastUtil {

    public static void show(Activity activity, String msg) {
        Toast toast = Toast.makeText(activity, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
