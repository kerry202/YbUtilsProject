package com.youyicheng.KaoLiao.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.adapters.EvaluateAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExperienceActivity extends BaseActivity {


    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;
    @BindView(R.id.periphery_recycler)
    RecyclerView peripheryRecycler;
    @BindView(R.id.experience_back)
    RelativeLayout experienceBack;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.shared_rl)
    RelativeLayout sharedRl;
    @BindView(R.id.experience_rl)
    RelativeLayout experienceRl;
    @BindView(R.id.live_rl)
    RelativeLayout liveRl;
    @BindView(R.id.colltion_rl)
    RelativeLayout colltionRl;
    @BindView(R.id.experience_buttom_ll)
    LinearLayout experienceButtomLl;
    @BindView(R.id.details_user_icon)
    ImageView detailsUserIcon;
    @BindView(R.id.details_add_follow)
    Button detailsAddFollow;
    @BindView(R.id.details_user_name)
    TextView detailsUserName;
    @BindView(R.id.details_experience_value)
    TextView detailsExperienceValue;
    @BindView(R.id.details_consultation_tv)
    TextView detailsConsultationTv;
    @BindView(R.id.details_data_tv)
    TextView detailsDataTv;

    @BindView(R.id.student_detail)
    RelativeLayout student_detail;

    @Override
    protected int getLayoutId() {
        return R.layout.experience_layout;
    }

    @Override
    protected void initView() {

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            list.add("");
        }

        ArrayList<String> list2 = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            list2.add("");
        }

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        experienceRecycler.setLayoutManager(linearLayoutManager1);

        EvaluateAdapter evaluateAdapter = new EvaluateAdapter(list);

        experienceRecycler.setAdapter(evaluateAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        peripheryRecycler.setLayoutManager(linearLayoutManager2);
        DetailsAdapter detailsAdapter = new DetailsAdapter(list2);
        peripheryRecycler.setAdapter(detailsAdapter);


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.experience_back, R.id.shared_rl, R.id.student_detail, R.id.experience_rl, R.id.live_rl, R.id.colltion_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.experience_back:
                finish();
                break;
            case R.id.shared_rl:
                break;
            case R.id.experience_rl:
                break;
            case R.id.live_rl:
                break;
            case R.id.colltion_rl:
                break;
            case R.id.student_detail:
                startActivity(new Intent(activity,StudentDetailActivity.class));
                break;
        }
    }
}
