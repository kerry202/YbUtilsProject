package com.youyicheng.KaoLiao.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.HomePagerAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.fragemnt.ConsultationFragment;
import com.youyicheng.KaoLiao.fragemnt.DataFragment;
import com.youyicheng.KaoLiao.fragemnt.ExperienceFragment;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.GoodsListBean;
import com.youyicheng.KaoLiao.module.LoginBean;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearshResultActivity extends BaseActivity {

    @BindView(R.id.searsh_back)
    RelativeLayout searshBack;

    @BindView(R.id.srarh_result_rl)
    RelativeLayout srarh_result_rl;


    @BindView(R.id.input_searsh_str)
    EditText inputSearshStr;
    @BindView(R.id.clean_iv)
    ImageView cleanIv;

    @BindView(R.id.home_jyt__line)
    View home_jyt__line;
    @BindView(R.id.home_1v1__line)
    View home_1v1__line;
    @BindView(R.id.home_data__line)
    View home_data__line;
    @BindView(R.id.home_jyt_tv)
    TextView home_jyt_tv;
    @BindView(R.id.home_1v1_tv)
    TextView home_1v1_tv;
    @BindView(R.id.home_data_tv)
    TextView home_data_tv;

    @BindView(R.id.searsh_pager)
    ViewPager searshPager;

    @BindView(R.id.home_jyt_ll)
    LinearLayout home_jyt_ll;
    @BindView(R.id.home_1v1_ll)
    LinearLayout home_1v1_ll;
    @BindView(R.id.home_data_ll)
    LinearLayout home_data_ll;


    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private HomePagerAdapter homePagerAdapter;
    private String goods_type;
    private String token;
    private String order;
    private String keyword;
    private ExperienceFragment experienceFragment;
    private ConsultationFragment consultationFragment;
    private DataFragment dataFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.searsh_result_layout;
    }

    @Override
    protected void initView() {

//        intent.putExtra("goods_type", "" + state);
//        intent.putExtra("token", token);
//        intent.putExtra("order", "0");
//        intent.putExtra("keyword", inputEt.getText().toString());

        goods_type = getIntent().getStringExtra("goods_type");
        token = getIntent().getStringExtra("token");
        order = getIntent().getStringExtra("order");
        keyword = getIntent().getStringExtra("keyword");

        experienceFragment = new ExperienceFragment();
        experienceFragment.setOrderID(order);
        consultationFragment = new ConsultationFragment();
        dataFragment = new DataFragment();
        consultationFragment.setOrderID(order);
        dataFragment.setOrderID(order);
        fragments.add(experienceFragment);

        titles.add("经验帖");
        titles.add("1v1咨询");
        titles.add("备考资料");
        fragments.add(consultationFragment);
        fragments.add(dataFragment);

        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(), fragments, titles);

        searshPager.setAdapter(homePagerAdapter);

        searshPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    setColor1();

                } else if (i == 1) {
                    setColor2();
                } else {
                    setColor3();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    protected void initData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("token", token);
        params.put("goods_type", goods_type);
        params.put("order", order);
        params.put("keyword", keyword);

        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.searchList, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     搜索结果 onNext  " + data);

                GoodsListBean goodsListBean = new Gson().fromJson(data, GoodsListBean.class);

                if (goodsListBean.data != null && goodsListBean.data.size() > 0) {
                    experienceFragment.setNewData(goodsListBean.data);
                }

            }

            @Override
            public void onError(String msg) {
                Logs.s("     搜索结果 onError  " + msg);

            }
        });

    }


    @OnClick({R.id.searsh_back, R.id.srarh_result_rl, R.id.home_jyt_ll, R.id.home_1v1_ll, R.id.home_data_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.searsh_back:
                finish();
                break;
            case R.id.srarh_result_rl:
                inputSearshStr.setText("");
                break;
            case R.id.home_jyt_ll:
                searshPager.setCurrentItem(0);
                setColor1();
                break;
            case R.id.home_1v1_ll:
                searshPager.setCurrentItem(1);
                setColor2();
                break;
            case R.id.home_data_ll:
                searshPager.setCurrentItem(2);
                setColor3();
                break;
        }
    }

    private void setColor3() {
        home_jyt__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.INVISIBLE);
        home_data__line.setVisibility(View.VISIBLE);
        home_jyt_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        home_1v1_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        home_data_tv.setTextColor(activity.getResources().getColor(R.color.gray_333));

        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(true);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(false);
    }

    private void setColor2() {
        home_jyt__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.VISIBLE);
        home_data__line.setVisibility(View.INVISIBLE);
        home_jyt_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        home_1v1_tv.setTextColor(activity.getResources().getColor(R.color.gray_333));
        home_data_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(true);
    }

    private void setColor1() {
        home_jyt__line.setVisibility(View.VISIBLE);
        home_data__line.setVisibility(View.INVISIBLE);
        home_1v1__line.setVisibility(View.INVISIBLE);
        home_jyt_tv.setTextColor(activity.getResources().getColor(R.color.gray_333));
        home_1v1_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        home_data_tv.setTextColor(activity.getResources().getColor(R.color.gray_999));
        TextPaint tp1 = home_data_tv.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = home_jyt_tv.getPaint();
        tp2.setFakeBoldText(true);
        TextPaint tp3 = home_1v1_tv.getPaint();
        tp3.setFakeBoldText(false);
    }
}
