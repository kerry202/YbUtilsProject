package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnLoadMoreListener;
import com.yb.refrsh.listener.OnRefreshListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.adapters.DetailsPagerAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.DetailBean;
import com.youyicheng.KaoLiao.module.FollowBean;
import com.youyicheng.KaoLiao.module.LoginBean;
import com.youyicheng.KaoLiao.ui.StudentDetailActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class DetailsConsultationFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.details_banner_pager)
    ViewPager detailsBannerPager;
    @BindView(R.id.details_banner_index)
    TextView detailsBannerIndex;
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
    @BindView(R.id.details_recycler)
    RecyclerView detailsRecycler;

    @BindView(R.id.student_detail)
    RelativeLayout student_detail;
    Unbinder unbinder;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.major_tv)
    TextView majorTv;
    @BindView(R.id.des1)
    TextView des1;
    @BindView(R.id.des2)
    TextView des2;
    private int pagerCount;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            pagerCount++;
            if (pagerCount == 4) {
                pagerCount = 0;
            }
            detailsBannerPager.setCurrentItem(pagerCount);
            handler.sendEmptyMessageDelayed(1, 3000);
        }
    };
    private DetailsPagerAdapter detailsPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.details_consultation_layout;
    }

    @Override
    protected void initView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        detailsRecycler.setLayoutManager(linearLayoutManager);


    }

    private String goods_id;

    public void getGoodsId(String goods_id) {
        this.goods_id = goods_id;
    }

    private boolean isFollow;

    @Override
    protected void initData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id);
        HttpUtils.getInstance().sendRequest(getActivity(), params, RequestState.STATE_DIALOG, MyInterface.goods_detail, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                DetailBean detailBean = new Gson().fromJson(data, DetailBean.class);

                Logs.s("     商品详情 onNext  " + detailBean);
                if (detailBean != null) {
                    setPager(detailBean);
                }


                List<DetailBean.DataBean.GoodsBean> goods = detailBean.data.goods;

                DetailsAdapter detailsAdapter = new DetailsAdapter(getActivity(), goods);
                detailsRecycler.setAdapter(detailsAdapter);

                DetailBean.DataBean.SeniorBean senior = detailBean.data.senior;

                SPUtils.setParam(getActivity(), "uid", senior.uid + "");

                Glide.with(getActivity())
                        .load(senior.head_img)
                        .into(detailsUserIcon);
                detailsUserName.setText(senior.nickname);
                majorTv.setText(senior.school + "|" + senior.major);
                if (senior.is_followed == 0) {
                    isFollow = true;
                    detailsAddFollow.setText("+关注");
                    detailsAddFollow.setBackground(getActivity().getResources().getDrawable(R.drawable.circle_red));
                    detailsAddFollow.setTextColor(getActivity().getResources().getColor(R.color.white));
                } else {
                    isFollow = false;
                    detailsAddFollow.setText("已关注");
                    detailsAddFollow.setBackground(getActivity().getResources().getDrawable(R.drawable.circle_gray_d));
                    detailsAddFollow.setTextColor(getActivity().getResources().getColor(R.color.gray_666));
                }
                DetailBean.DataBean data1 = detailBean.data;
                tv1.setText("¥ " + data1.price + "/30分钟");
                tv3.setText("" + data1.content);
            }

            @Override
            public void onError(String msg) {
                Logs.s("     商品详情 onError  " + msg);
            }
        });
    }

    private void setPager(DetailBean detailBean) {

        List<DetailBean.DataBean.GoodsSlideBean> goods_slide = detailBean.data.goods_slide;
        detailsPagerAdapter = new DetailsPagerAdapter(getActivity(), goods_slide);
        detailsBannerPager.setAdapter(detailsPagerAdapter);
        handler.sendEmptyMessageDelayed(1, 3000);
        detailsBannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                detailsBannerIndex.setText(i + "/" + goods_slide.size());

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }


    @OnClick({R.id.details_add_follow, R.id.student_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_add_follow:
                if (isFollow) {
                    goFoloow(MyInterface.addFollow);
                } else {
                    goFoloow(MyInterface.delFollow);
                }

                break;
            case R.id.student_detail:
                startActivity(new Intent(getActivity(), StudentDetailActivity.class));
                break;
        }
    }

    private void goFoloow(String url) {

        String token = (String) SPUtils.getParam(getActivity(), "token", "");
        String uid = (String) SPUtils.getParam(getActivity(), "uid", "");
        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("uid", uid);
        HttpUtils.getInstance().sendRequest(getActivity(), params, RequestState.STATE_DIALOG, url, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                FollowBean followBean = new Gson().fromJson(data, FollowBean.class);
                Logs.s("     关注 onNext  " + followBean);
                if (followBean.result.equals("SUCCESS")) {
                    isFollow = !isFollow;
                } else {
                    ToastUtil.show(getActivity(), followBean.message);
                }
                EventBus.getDefault().post(new MyEvents<>());


            }

            @Override
            public void onError(String msg) {
                Logs.s("     关注 onError  " + msg);

            }
        });
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }


}
