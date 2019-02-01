package com.youyicheng.KaoLiao.ui;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.yb.refrsh.SmartRefreshLayout;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnRefreshListener;
import com.yb.refrsh.listener.OnRefreshLoadMoreListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.EvaluateAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.ColltionBean;
import com.youyicheng.KaoLiao.module.CommentBean;
import com.youyicheng.KaoLiao.module.DetailBean;
import com.youyicheng.KaoLiao.module.FollowBean;
import com.youyicheng.KaoLiao.module.LikesBean;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ExperienceActivity extends BaseActivity implements OnRefreshListener, OnRefreshLoadMoreListener {


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
    @BindView(R.id.addlieks_tv)
    TextView addlieks_tv;

    @BindView(R.id.colltion_tv)
    TextView colltion_tv;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String goods_id;

    @Override
    protected int getLayoutId() {
        return R.layout.experience_layout;
    }

    @Override
    protected void initView() {

        goods_id = getIntent().getStringExtra("goods_id");


        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        experienceRecycler.setLayoutManager(linearLayoutManager1);

        evaluateAdapter = new EvaluateAdapter(activity, dataBeanArrayList);
//
        experienceRecycler.setAdapter(evaluateAdapter);

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
        addColltion();
        addLike();
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id);
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.goods_detail, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                DetailBean detailBean = new Gson().fromJson(data, DetailBean.class);

                Logs.s("     经验帖 onNext  " + detailBean);

                DetailBean.DataBean.SeniorBean senior = detailBean.data.senior;
                Glide.with(activity)
                        .load(detailBean.data.goods_img)
                        .into(im1);
                SPUtils.setParam(activity, "uid", senior.uid + "");

                Glide.with(activity)
                        .load(senior.head_img)
                        .into(detailsUserIcon);

                detailsUserName.setText(senior.nickname);
                majorTv.setText(senior.school + "|" + senior.major);
                title1.setText(detailBean.data.goods_name);
                tv2.setText(detailBean.data.content);

                des1.setText(detailBean.data.intro + "");

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

        getCommentData();
    }

    private List<CommentBean.DataBean> dataBeanArrayList = new ArrayList<>();
    private EvaluateAdapter evaluateAdapter;
    private int state = 0;

    private void getCommentData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id + "");
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.commentList, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                CommentBean commentBean = new Gson().fromJson(data, CommentBean.class);
                Logs.s("     评论列表 onNext  " + commentBean);
                dataBeanArrayList = commentBean.data;

                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    commentCount.setText("研友评价 (" + dataBeanArrayList.size() + ")");
                    if (state == 0) {
                        evaluateAdapter.setNewData(dataBeanArrayList);
                    } else {
                        evaluateAdapter.addData(dataBeanArrayList);
                    }
                } else {
                    commentCount.setText("研友评价 (0)");
                }
                refreshLayout.finishLoadMore();

            }

            @Override
            public void onError(String msg) {
                Logs.s("     评论列表 onError  " + msg);
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

                addLike();

                break;

            case R.id.details_add_follow:
                if (isFollow) {
                    goFoloow(MyInterface.addFollow);
                } else {
                    goFoloow(MyInterface.delFollow);
                }
                break;
            case R.id.colltion_rl:
                addColltion();
                break;
            case R.id.student_detail:
                startActivity(new Intent(activity, StudentDetailActivity.class));
                break;
        }
    }

    private void addColltion() {
        HashMap<String, String> params = new HashMap<>();
        String token = (String) SPUtils.getParam(activity, "token", "");
        params.put("token", token);
        params.put("goods_id", goods_id + "");
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.addColltion, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                ColltionBean colltionBean = new Gson().fromJson(data, ColltionBean.class);
                if (colltionBean.data.is_favorite) {
                    ToastUtil.show(activity, "收藏成功");
                    colltion_tv.setText("已收藏");

                    Drawable drawable = getResources().getDrawable(R.mipmap.colltion_yes_icon);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    colltion_tv.setCompoundDrawables(drawable, null, null, null);

                } else {

                    Drawable drawable = getResources().getDrawable(R.mipmap.colltion_white_icon);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    colltion_tv.setCompoundDrawables(drawable, null, null, null);
                    ToastUtil.show(activity, "取消收藏");
                    colltion_tv.setText("收藏");
                }
                EventBus.getDefault().post(new MyEvents<>());
                Logs.s("     收藏 onNext  " + data);

            }

            @Override
            public void onError(String msg) {
                Logs.s("     收藏 onError  " + msg);

            }
        });
    }

    private void addLike() {

        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id);
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.addLikes, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     点赞 onNext  " + data);

                LikesBean likesBean = new Gson().fromJson(data, LikesBean.class);

                if (likesBean != null) {
                    if (likesBean.data.is_likes) {
                        Drawable drawable = getResources().getDrawable(R.mipmap.likes_yes_icon);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        addlieks_tv.setCompoundDrawables(drawable, null, null, null);
                    } else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.live_whie_icon);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        addlieks_tv.setCompoundDrawables(drawable, null, null, null);
                    }
                }

            }

            @Override
            public void onError(String msg) {
                Logs.s("     点赞 onError  " + msg);

            }
        });
    }

    private void goFoloow(String url) {

        String token = (String) SPUtils.getParam(activity, "token", "");
        String uid = (String) SPUtils.getParam(activity, "uid", "");
        HashMap<String, String> params = new HashMap<>();
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
                EventBus.getDefault().post(new MyEvents<>());

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

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        state = 1;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
