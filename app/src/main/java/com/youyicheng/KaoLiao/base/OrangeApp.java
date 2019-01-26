package com.youyicheng.KaoLiao.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;

public class OrangeApp extends MultiDexApplication {


    public static EventBus mEventBus = EventBus.getDefault();

    public static Context mContext ;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }
}
