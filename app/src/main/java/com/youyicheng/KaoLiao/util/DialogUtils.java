package com.youyicheng.KaoLiao.util;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.youyicheng.KaoLiao.listener.HomeListener;
import com.youyicheng.KaoLiao.R;


public class DialogUtils {


    private static CustomPopWindow popWindow;

    public static void showDialog(final Activity activity, ImageView imageView, HomeListener listener) {

        View view = LayoutInflater.from(activity).inflate(R.layout.home_dialog_layout, null);

        popWindow = new CustomPopWindow.PopupWindowBuilder(activity)
                .setView(view)
                .setFocusable(true)
                .setOutsideTouchable(true)
                .create();


        view.findViewById(R.id.remen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.remen(1);
                popWindow.dissmiss();
            }
        });

        view.findViewById(R.id.new_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.newData(2);
                popWindow.dissmiss();
            }
        });
        popWindow.showAsDropDown(imageView, 0, 10);


    }

}