package com.youyicheng.KaoLiao.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.HomePagerAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.fragemnt.DetailsConsultationFragment;
import com.youyicheng.KaoLiao.fragemnt.DetailsEvaluateFragment;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity {

    @BindView(R.id.details_back)
    RelativeLayout detailsBack;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.details_shared_tv)
    TextView detailsSharedTv;
    @BindView(R.id.details_colletion_tv)
    TextView detailsColletionTv;
    @BindView(R.id.details_msg_tv)
    TextView detailsMsgTv;
    @BindView(R.id.details_buttom_rl)
    RelativeLayout detailsButtomRl;
    @BindView(R.id.details_viewpager)
    ViewPager detailsPager;
    @BindView(R.id.go_buy)
    Button goBuy;
    @BindView(R.id.details_bk_line)
    View details_bk_line;
    @BindView(R.id.details_pl_line)
    View details_pl_line;

    @BindView(R.id.details_bk_tv)
    TextView details_bk_tv;

    @BindView(R.id.details_pl_tv)
    TextView details_pl_tv;

    @BindView(R.id.home_jyt_ll)
    LinearLayout home_jyt_ll;
    @BindView(R.id.home_1v1_ll)
    LinearLayout home_1v1_ll;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private HomePagerAdapter homePagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.details_layout;
    }

    @Subscribe
    public void onEvent(MyEvents event) {


        Logs.s("   收到消息:   " + event.errmsg);
    }

    @Override
    protected void initView() {

        String stringExtra = getIntent().getStringExtra("titleName");


        fragments.add(new DetailsConsultationFragment());
        fragments.add(new DetailsEvaluateFragment());
        titles.add(stringExtra);
        titles.add("评价");
        details_bk_tv.setText(stringExtra);
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragments, titles);

        detailsPager.setAdapter(homePagerAdapter);

        detailsPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    setColor1();
                } else if (i == 1) {
                    setColor2();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void setColor2() {
        details_pl_line.setVisibility(View.VISIBLE);
        details_bk_line.setVisibility(View.INVISIBLE);
        details_bk_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        details_pl_tv.setTextColor(activity.getResources().getColor(R.color.gray_333));
        TextPaint tp1 = details_bk_tv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = details_pl_tv.getPaint();
        tp2.setFakeBoldText(true);
    }

    private void setColor1() {
        details_bk_line.setVisibility(View.VISIBLE);
        details_pl_line.setVisibility(View.INVISIBLE);
        details_bk_tv.setTextColor(activity.getResources().getColor(R.color.gray_333));
        details_pl_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        TextPaint tp1 = details_bk_tv.getPaint();
        tp1.setFakeBoldText(true);
        TextPaint tp2 = details_pl_tv.getPaint();
        tp2.setFakeBoldText(false);
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.details_back, R.id.details_shared_tv, R.id.go_buy, R.id.details_colletion_tv, R.id.details_msg_tv, R.id.home_jyt_ll, R.id.home_1v1_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.details_back:
                finish();
                break;
            case R.id.details_shared_tv:
                break;
            case R.id.details_colletion_tv:
                break;
            case R.id.details_msg_tv:
                break;
            case R.id.go_buy:
                startActivity(new Intent(this, OkBuyActivity.class));
                break;
            case R.id.home_jyt_ll:
                detailsPager.setCurrentItem(0);
                setColor1();
                break;
            case R.id.home_1v1_ll:
                detailsPager.setCurrentItem(1);
                setColor2();
                break;
        }
    }

}
