package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.adapters.DetailsPagerAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.StudentDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 *
 * */
public class DetailsConsultationFragment extends BaseFragment {
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

    @Override
    protected int getLayoutId() {
        return R.layout.details_consultation_layout;
    }

    @Override
    protected void initView() {

        DetailsPagerAdapter detailsPagerAdapter = new DetailsPagerAdapter();

        detailsBannerPager.setAdapter(detailsPagerAdapter);

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("");
        }

        handler.sendEmptyMessageDelayed(1, 3000);

        detailsBannerPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        detailsRecycler.setLayoutManager(linearLayoutManager);
        DetailsAdapter detailsAdapter = new DetailsAdapter(list);
        detailsRecycler.setAdapter(detailsAdapter);

    }

    @Override
    protected void initData() {

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
                break;
            case R.id.student_detail:
                startActivity(new Intent(getActivity(),StudentDetailActivity.class));
                break;
        }
    }
}
