package com.youyicheng.KaoLiao.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.youyicheng.KaoLiao.R;


public class DialogUtils {


    public static void showDialog(final Activity activity, ImageView imageView) {

        View view = LayoutInflater.from(activity).inflate(R.layout.home_dialog_layout, null);

        CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(activity)
                .setView(view)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();
        popWindow.showAsDropDown(imageView, 0, 10);


    }

}