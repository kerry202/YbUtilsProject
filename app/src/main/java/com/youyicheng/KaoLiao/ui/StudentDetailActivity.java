package com.youyicheng.KaoLiao.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.StudentAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StudentDetailActivity extends BaseActivity {
    @BindView(R.id.me_set)
    ImageView meSet;
    @BindView(R.id.me_msg)
    ImageView meMsg;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_flag)
    TextView userFlag;
    @BindView(R.id.student_recycler)
    RecyclerView studentRecycler;
    @BindView(R.id.follow_tv)
    TextView followTv;

    @Override
    protected int getLayoutId() {
        return R.layout.student_detail_layout;
    }

    @Override
    protected void initView() {

        studentRecycler.setLayoutManager(new LinearLayoutManager(activity));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        StudentAdapter studentAdapter = new StudentAdapter(arrayList);
        studentRecycler.setAdapter(studentAdapter);

    }


    @Override
    protected void initData() {

    }


    @OnClick({R.id.me_set, R.id.follow_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.me_set:
                finish();
                break;
            case R.id.follow_tv:
                ToastUtil.show(activity, "follow");
                break;
        }
    }
}
