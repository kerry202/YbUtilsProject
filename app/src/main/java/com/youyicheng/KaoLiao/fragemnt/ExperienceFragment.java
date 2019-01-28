package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.recycler.baseholder.BaseQuickAdapter;
import com.yb.refrsh.SmartRefreshLayout;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnLoadMoreListener;
import com.yb.refrsh.listener.OnRefreshListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.ExperienceAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.GoodsListBean;
import com.youyicheng.KaoLiao.module.RegisterModule;
import com.youyicheng.KaoLiao.ui.ExperienceActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExperienceFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private List<GoodsListBean.DataBean> arrayList = new ArrayList<>();
    private ExperienceAdapter experienceAdapter;
    private int state = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_experience_layout;
    }


    @Override
    protected void initView() {

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        experienceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


        experienceAdapter = new ExperienceAdapter(getActivity(), arrayList);

        experienceRecycler.setAdapter(experienceAdapter);

        experienceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                GoodsListBean.DataBean dataBean = arrayList.get(position);

                Intent intent = new Intent(getActivity(), ExperienceActivity.class);
                intent.putExtra("goods_id", dataBean.goods_id + "");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("goods_type", "0");
        params.put("order", "0");

        HttpUtils.getInstance().sendRequest(getActivity(), params, RequestState.STATE_DIALOG, MyInterface.GoogsList, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                GoodsListBean registerModule = new Gson().fromJson(data, GoodsListBean.class);
                Logs.s("     商品列表 onNext  " + registerModule);
                if (registerModule != null && registerModule.result.equals("SUCCESS")) {
                    if (state == 1) {
                        arrayList = registerModule.data;
                        experienceAdapter.setNewData(arrayList);
                        refreshLayout.finishRefresh();
                    } else if (state == 2) {
                        arrayList.addAll(registerModule.data);
                        experienceAdapter.setNewData(arrayList);
                        refreshLayout.finishLoadMore();
                    } else {
                        arrayList = registerModule.data;
                        experienceAdapter.setNewData(arrayList);
                    }

                } else {
                    ToastUtil.show(getActivity(), registerModule.message);
                }
                state = 0;
            }

            @Override
            public void onError(String msg) {
                Logs.s("     商品列表 onError  " + msg);
                state = 0;
            }
        });

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        state = 2;
        initData();
        Logs.s(" ExperienceFragment  onLoadMore   ");
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        state = 1;
        initData();
        Logs.s(" ExperienceFragment  onRefresh   ");
    }

}
