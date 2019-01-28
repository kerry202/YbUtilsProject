package com.youyicheng.KaoLiao.ui;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.recycler.baseholder.BaseQuickAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.AddressAdapter;
import com.youyicheng.KaoLiao.adapters.CityAdapter;
import com.youyicheng.KaoLiao.adapters.MajorAdapter;
import com.youyicheng.KaoLiao.adapters.SchoolAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.AddressBean;
import com.youyicheng.KaoLiao.module.CityBean;
import com.youyicheng.KaoLiao.module.LoginBean;
import com.youyicheng.KaoLiao.module.MajorBean;
import com.youyicheng.KaoLiao.module.SchoolBean;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SelectAddressActivity extends BaseActivity {

    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.contact_server_tv)
    TextView contactServerTv;
    @BindView(R.id.address_searsh)
    RelativeLayout addressSearsh;
    @BindView(R.id.address_recycler)
    RecyclerView addressRecycler;
    private int state;
    private String url;
    List<AddressBean.DataBean> dataBeans1 = new ArrayList<>();
    List<MajorBean.DataBean> dataBeans2 = new ArrayList<>();
    List<CityBean.DataBean> dataBeans3 = new ArrayList<>();
    List<SchoolBean.DataBean> dataBeans4 = new ArrayList<>();
    private AddressAdapter addressAdapter;
    private MajorAdapter majorAdapter;
    private CityAdapter cityAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.select_address_layout;
    }

    @Override
    protected void initView() {

        titleSend.setVisibility(View.GONE);
        titleName.setText(getIntent().getStringExtra("titleName"));
        state = getIntent().getIntExtra("state", 0);
        addressRecycler.setLayoutManager(new LinearLayoutManager(activity));

    }


    @Override
    protected void initData() {
        HashMap<String, Object> params = new HashMap<>();
        String token = (String) SPUtils.getParam(activity, "token", "");
        String category_id = getIntent().getStringExtra("category_id");
        String major_id = getIntent().getStringExtra("major_id");
        String city_id = getIntent().getStringExtra("city_id");
        params.put("token", token);

        if (state == 1) {
            url = MyInterface.getCategory;
        } else if (state == 2) {
            url = MyInterface.getMajor;
            params.put("category", category_id);
        } else if (state == 3) {
            url = MyInterface.getCity;
            params.put("category", category_id);
            params.put("major", major_id);
        } else if (state == 4) {
            url = MyInterface.getSchool;
            params.put("category", category_id);
            params.put("major", major_id);
            params.put("city", city_id);
        }

        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, url, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     选择数据 onNext  " + data);
                if (state == 1) {
                    AddressBean addressBean = new Gson().fromJson(data, AddressBean.class);
                    if (addressBean.data != null) {
                        dataBeans1 = addressBean.data;
                        addressAdapter = new AddressAdapter(dataBeans1, 0);
                        addressRecycler.setAdapter(addressAdapter);
                        setOnclick(addressAdapter);
                    }
                } else if (state == 2) {

                    MajorBean majorBean = new Gson().fromJson(data, MajorBean.class);
                    if (majorBean.data != null) {
                        dataBeans2 = majorBean.data;
                        majorAdapter = new MajorAdapter(dataBeans2, 0);
                        addressRecycler.setAdapter(majorAdapter);
                        setOnclick(majorAdapter);
                    }
                } else if (state == 3) {

                    CityBean cityBean = new Gson().fromJson(data, CityBean.class);
                    if (cityBean.data != null) {
                        dataBeans3 = cityBean.data;
                        cityAdapter = new CityAdapter(dataBeans3, 0);
                        addressRecycler.setAdapter(cityAdapter);
                        setOnclick(cityAdapter);
                    }
                } else if (state == 4) {

                    SchoolBean schoolBean = new Gson().fromJson(data, SchoolBean.class);
                    if (schoolBean.data != null) {
                        dataBeans4 = schoolBean.data;
                        SchoolAdapter  schoolAdapter = new SchoolAdapter(dataBeans4, 0);
                        addressRecycler.setAdapter(schoolAdapter);
                        setOnclick(schoolAdapter);
                    }
                }

            }

            @Override
            public void onError(String msg) {
                Logs.s("     登陆 onError  " + msg);

            }
        });
    }


    private void setOnclick(BaseQuickAdapter adapter) {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                if (state == 1) {
                    intent.putExtra("category_name", dataBeans1.get(position).category_name);
                    intent.putExtra("category_id", dataBeans1.get(position).id + "");
                } else if (state == 2) {
                    intent.putExtra("major_name", dataBeans2.get(position).major_name);
                    intent.putExtra("major_id", dataBeans2.get(position).id + "");
                } else if (state == 3) {
                    intent.putExtra("city_name", dataBeans3.get(position).city_name);
                    intent.putExtra("city_id", dataBeans3.get(position).id + "");
                } else if (state == 4) {
                    intent.putExtra("school_name", dataBeans4.get(position).school_name);
                    intent.putExtra("school_id", dataBeans4.get(position).id + "");
                }

                setResult(RESULT_OK, intent);
                finish();

            }
        });

    }


    @OnClick({R.id.title_back, R.id.contact_server_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.contact_server_tv:
                break;
        }
    }
}
