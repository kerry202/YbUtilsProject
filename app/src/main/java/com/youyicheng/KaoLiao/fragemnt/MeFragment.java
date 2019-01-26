package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yb.refrsh.SmartRefreshLayout;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnLoadMoreListener;
import com.yb.refrsh.listener.OnRefreshListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.ApplyActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MeFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
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
    @BindView(R.id.colltion_count)
    TextView colltionCount;
    @BindView(R.id.colltion_line)
    View colltionLine;
    @BindView(R.id.me_colltion_ll)
    LinearLayout meColltionLl;
    @BindView(R.id.follow_count)
    TextView followCount;
    @BindView(R.id.follow_line)
    View followLine;
    @BindView(R.id.me_follow_ll)
    LinearLayout meFollowLl;
    @BindView(R.id.order_count)
    TextView orderCount;
    @BindView(R.id.order_line)
    View orderLine;
    @BindView(R.id.me_order_ll)
    LinearLayout meOrderLl;
    @BindView(R.id.send_count)
    TextView sendCount;
    @BindView(R.id.send_line)
    View sendLine;
    @BindView(R.id.me_send_ll)
    LinearLayout meSendLl;

    @BindView(R.id.me_colltion_tv)
    TextView meColltionTv;
    @BindView(R.id.me_follow_tv)
    TextView meFollowTv;
    @BindView(R.id.me_order_tv)
    TextView meOrderTv;
    @BindView(R.id.me_send_tv)
    TextView meSendTv;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private MeColltionFragment meColltionFragment;
    private MeFollowFragment meFollowFragment;
    private MeOrderFragment meOrderFragment;
    private MeSendFragment meSendFragment;
    private FragmentManager childFragmentManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me_layout;
    }

    @Override
    protected void initView() {

        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//不做磁盘缓存
                .skipMemoryCache(true);//不做内存缓存
        Glide.with(getActivity())
                .load(R.mipmap.test_icon)
                .apply(mRequestOptions)
                .into(userPhoto);


        refreshLayout.autoRefresh();
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        meColltionFragment = new MeColltionFragment();
        meFollowFragment = new MeFollowFragment();
        meOrderFragment = new MeOrderFragment();
        meSendFragment = new MeSendFragment();

        childFragmentManager = getChildFragmentManager();

        FragmentTransaction fragmentTransaction = childFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.me_fl, meColltionFragment);
        fragmentTransaction.add(R.id.me_fl, meFollowFragment);
        fragmentTransaction.add(R.id.me_fl, meOrderFragment);
        fragmentTransaction.add(R.id.me_fl, meSendFragment);

        fragmentTransaction.show(meColltionFragment).hide(meFollowFragment).hide(meOrderFragment).hide(meSendFragment)
                .commit();

        colltionLine.setVisibility(View.VISIBLE);

    }

    @Subscribe
    public void onEvent(MyEvents event) {


        Logs.s("   收到消息:   " + event.errmsg);
    }


    public void hideLine() {
        colltionLine.setVisibility(View.INVISIBLE);
        followLine.setVisibility(View.INVISIBLE);
        orderLine.setVisibility(View.INVISIBLE);
        sendLine.setVisibility(View.INVISIBLE);
    }


    @Override
    protected void initData() {

    }


    @OnClick({R.id.user_photo, R.id.me_colltion_ll, R.id.me_msg, R.id.me_follow_ll, R.id.me_order_ll, R.id.me_send_ll})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.user_photo:

                break;
            case R.id.me_msg:
                startActivity(new Intent(getActivity(), ApplyActivity.class));
                break;
            case R.id.me_colltion_ll:
                hideLine();
                setBold1();

                colltionLine.setVisibility(View.VISIBLE);
                childFragmentManager.beginTransaction().show(meColltionFragment).hide(meFollowFragment).hide(meOrderFragment).hide(meSendFragment)
                        .commit();

                break;
            case R.id.me_follow_ll:
                hideLine();
                setBold2();

                followLine.setVisibility(View.VISIBLE);
                childFragmentManager.beginTransaction().show(meFollowFragment).hide(meColltionFragment).hide(meOrderFragment).hide(meSendFragment)
                        .commit();
                break;
            case R.id.me_order_ll:
                hideLine();
                setBold3();

                orderLine.setVisibility(View.VISIBLE);
                childFragmentManager.beginTransaction().show(meOrderFragment).hide(meColltionFragment).hide(meFollowFragment).hide(meSendFragment)
                        .commit();
                break;
            case R.id.me_send_ll:
                hideLine();
                setBold4();

                sendLine.setVisibility(View.VISIBLE);
                childFragmentManager.beginTransaction().show(meSendFragment).hide(meColltionFragment).hide(meFollowFragment).hide(meOrderFragment)
                        .commit();
                break;
        }
    }

    private void setBold1() {

        TextPaint tp1 = meColltionTv.getPaint();
        tp1.setFakeBoldText(true);
        TextPaint tp5 = colltionCount.getPaint();
        tp5.setFakeBoldText(true);

        meColltionTv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        colltionCount.setTextColor(getActivity().getResources().getColor(R.color.gray_333));


        TextPaint tp2 = meFollowTv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp6 = followCount.getPaint();
        tp6.setFakeBoldText(false);
        meFollowTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        followCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp3 = meOrderTv.getPaint();
        tp3.setFakeBoldText(false);
        TextPaint tp7 = orderCount.getPaint();
        tp7.setFakeBoldText(false);
        meOrderTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        orderCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp4 = meSendTv.getPaint();
        tp4.setFakeBoldText(false);
        TextPaint tp8 = sendCount.getPaint();
        tp8.setFakeBoldText(false);
        meSendTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        sendCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

    }

    private void setBold2() {
        TextPaint tp1 = meColltionTv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp5 = colltionCount.getPaint();
        tp5.setFakeBoldText(false);

        meColltionTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        colltionCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));


        TextPaint tp2 = meFollowTv.getPaint();
        tp2.setFakeBoldText(true);
        TextPaint tp6 = followCount.getPaint();
        tp6.setFakeBoldText(true);
        meFollowTv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        followCount.setTextColor(getActivity().getResources().getColor(R.color.gray_333));

        TextPaint tp3 = meOrderTv.getPaint();
        tp3.setFakeBoldText(false);
        TextPaint tp7 = orderCount.getPaint();
        tp7.setFakeBoldText(false);
        meOrderTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        orderCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp4 = meSendTv.getPaint();
        tp4.setFakeBoldText(false);
        TextPaint tp8 = sendCount.getPaint();
        tp8.setFakeBoldText(false);
        meSendTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        sendCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

    }

    private void setBold3() {

        TextPaint tp1 = meColltionTv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp5 = colltionCount.getPaint();
        tp5.setFakeBoldText(false);
        meColltionTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        colltionCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp2 = meFollowTv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp6 = followCount.getPaint();
        tp6.setFakeBoldText(false);
        meFollowTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        followCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp3 = meOrderTv.getPaint();
        tp3.setFakeBoldText(true);
        TextPaint tp7 = orderCount.getPaint();
        tp7.setFakeBoldText(true);
        meOrderTv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        orderCount.setTextColor(getActivity().getResources().getColor(R.color.gray_333));

        TextPaint tp4 = meSendTv.getPaint();
        tp4.setFakeBoldText(false);
        TextPaint tp8 = sendCount.getPaint();
        tp8.setFakeBoldText(false);
        meSendTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        sendCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

    }

    private void setBold4() {

        TextPaint tp1 = meColltionTv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp5 = colltionCount.getPaint();
        tp5.setFakeBoldText(false);

        meColltionTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        colltionCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));


        TextPaint tp2 = meFollowTv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp6 = followCount.getPaint();
        tp6.setFakeBoldText(false);
        meFollowTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        followCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp3 = meOrderTv.getPaint();
        tp3.setFakeBoldText(false);
        TextPaint tp7 = orderCount.getPaint();
        tp7.setFakeBoldText(false);
        meOrderTv.setTextColor(getActivity().getResources().getColor(R.color.gray_999));
        orderCount.setTextColor(getActivity().getResources().getColor(R.color.gray_999));

        TextPaint tp4 = meSendTv.getPaint();
        tp4.setFakeBoldText(true);
        TextPaint tp8 = sendCount.getPaint();
        tp8.setFakeBoldText(true);
        meSendTv.setTextColor(getActivity().getResources().getColor(R.color.gray_333));
        sendCount.setTextColor(getActivity().getResources().getColor(R.color.gray_333));

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore(); //上啦加载完成

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();  //下拉刷新完成
        refreshLayout.finishLoadMoreWithNoMoreData();  //已经没有更多了

    }
}
