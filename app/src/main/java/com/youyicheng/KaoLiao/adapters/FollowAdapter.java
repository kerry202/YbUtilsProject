package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.fragemnt.MeFragment;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.FollowBean;
import com.youyicheng.KaoLiao.module.MyColltionBean;
import com.youyicheng.KaoLiao.module.MyFollowBean;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

public class FollowAdapter extends BaseQuickAdapter<MyFollowBean.DataBean, BaseViewHolder> {
    Activity activity;
    List<MyFollowBean.DataBean> arrayList;
    private int adapterPosition;

    public FollowAdapter(Activity activity, @Nullable List<MyFollowBean.DataBean> arrayList) {
        super(R.layout.follow_adapter_layout, arrayList);
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyFollowBean.DataBean item) {


        adapterPosition = helper.getAdapterPosition();

        ImageView msg_photo = helper.getView(R.id.msg_photo);
        TextView title1 = helper.getView(R.id.title1);
        TextView des2 = helper.getView(R.id.des2);
        TextView cancle_follow = helper.getView(R.id.cancle_follow);

        Glide.with(activity)
                .load(item.head_img)
                .into(msg_photo);
        title1.setText(item.nickname);
        des2.setText(item.fans + "");

        cancle_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goFoloow(MyInterface.delFollow);

            }
        });
    }

    private void goFoloow(String url) {

        String token = (String) SPUtils.getParam(activity, "token", "");
        String uid = (String) SPUtils.getParam(activity, "uid", "");
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("uid", uid);
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, url, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                FollowBean followBean = new Gson().fromJson(data, FollowBean.class);
                Logs.s("     关注 onNext  " + followBean);
                Logs.s("     关注 adapterPosition  " + adapterPosition);
                Logs.s("     关注 arrayList  " + arrayList.size());
                if (followBean.result.equals("SUCCESS")) {

                    arrayList.remove(adapterPosition);

                    notifyDataSetChanged();

                    EventBus.getDefault().post(new MyEvents<>());
                } else {
                    ToastUtil.show(activity, followBean.message);
                }

            }

            @Override
            public void onError(String msg) {

                Logs.s("     关注 onError  " + msg);

            }
        });
    }

}
