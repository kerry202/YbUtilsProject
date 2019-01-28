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

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.DetailBean;
import com.youyicheng.KaoLiao.module.FollowBean;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.des1)
    TextView des1;
    @BindView(R.id.im1)
    ImageView im1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.comment_count)
    TextView commentCount;
    @BindView(R.id.click_all_comment_count)
    TextView clickAllCommentCount;
    @BindView(R.id.major_tv)
    TextView majorTv;
    private String goods_id;

    @Override
    protected int getLayoutId() {
        return R.layout.experience_layout;
    }

    @Override
    protected void initView() {

        goods_id = getIntent().getStringExtra("goods_id");

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

//        EvaluateAdapter evaluateAdapter = new EvaluateAdapter(list);

//        experienceRecycler.setAdapter(evaluateAdapter);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        peripheryRecycler.setLayoutManager(linearLayoutManager2);
//        DetailsAdapter detailsAdapter = new DetailsAdapter(activity,list2);
//        peripheryRecycler.setAdapter(detailsAdapter);


    }

    private boolean isFollow;

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id);
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.goods_detail, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                DetailBean detailBean = new Gson().fromJson(data, DetailBean.class);

                Logs.s("     经验帖 onNext  " + detailBean);

                DetailBean.DataBean.SeniorBean senior = detailBean.data.senior;

                SPUtils.setParam(activity, "uid", senior.uid + "");

                Glide.with(activity)
                        .load(senior.head_img)
                        .into(detailsUserIcon);
                detailsUserName.setText(senior.nickname);
                majorTv.setText(senior.school + "|" + senior.major);
                title1.setText(detailBean.data.goods_name);
                tv2.setText(detailBean.data.content);

                if (senior.is_followed == 0) {
                    isFollow = true;
                    detailsAddFollow.setText("+关注");
                    detailsAddFollow.setBackground(activity.getResources().getDrawable(R.drawable.circle_red));
                    detailsAddFollow.setTextColor(activity.getResources().getColor(R.color.white));
                } else {
                    isFollow = false;
                    detailsAddFollow.setText("已关注");
                    detailsAddFollow.setBackground(activity.getResources().getDrawable(R.drawable.circle_gray_d));
                    detailsAddFollow.setTextColor(activity.getResources().getColor(R.color.gray_666));
                }
                DetailBean.DataBean data1 = detailBean.data;
                tv3.setText("" + data1.content);


            }

            @Override
            public void onError(String msg) {
                Logs.s("     经验帖 onError  " + msg);
            }
        });
    }


    @OnClick({R.id.experience_back, R.id.details_add_follow, R.id.shared_rl, R.id.student_detail, R.id.experience_rl, R.id.live_rl, R.id.colltion_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.experience_back:
                finish();
                break;
            case R.id.shared_rl:
                break;
            case R.id.experience_rl:
                addComment();
                break;
            case R.id.live_rl:
                break;

            case R.id.details_add_follow:
                if (isFollow) {
                    goFoloow(MyInterface.addFollow);
                } else {
                    goFoloow(MyInterface.delFollow);
                }
                break;
            case R.id.colltion_rl:
                break;
            case R.id.student_detail:
                startActivity(new Intent(activity, StudentDetailActivity.class));
                break;
        }
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
                if (followBean.result.equals("SUCCESS")) {
                    isFollow = !isFollow;
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

    private void addComment() {
        String token = (String) SPUtils.getParam(activity, "token", "");
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("goods_id", goods_id + "");
        params.put("comment", "testcomment");

        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.addComment, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     评论 onNext  " + data);

            }

            @Override
            public void onError(String msg) {
                Logs.s("     评论 onError  " + msg);

            }
        });
    }

}
