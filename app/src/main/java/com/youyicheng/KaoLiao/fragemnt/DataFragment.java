package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
import com.youyicheng.KaoLiao.adapters.ConsultationAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.GoodsListBean;
import com.youyicheng.KaoLiao.ui.DetailsActivity;
import com.youyicheng.KaoLiao.util.HomeEvents;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DataFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ConsultationAdapter consultationAdapter;
    private List<GoodsListBean.DataBean> arrayList = new ArrayList<>();
    private int state = 0;


    private String order = "0";
    @Subscribe
    public void onEvent(HomeEvents event) {
        initData();
    }
    public void setNewData(List<GoodsListBean.DataBean> data) {
        this.arrayList = data;
    }


    public void setOrderID(String order) {
        this.order = order;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_experience_layout;
    }

    @Override
    protected void initView() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        experienceRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        consultationAdapter = new ConsultationAdapter(getActivity(), arrayList,0);
        experienceRecycler.setAdapter(consultationAdapter);

        consultationAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("titleName", "备考资料");
                intent.putExtra("goods_id", arrayList.get(position).goods_id + "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_type", "1");
        params.put("order", order);
        HttpUtils.getInstance().sendRequest(getActivity(), params, RequestState.STATE_DIALOG, MyInterface.GoogsList, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                GoodsListBean registerModule = new Gson().fromJson(data, GoodsListBean.class);
                Logs.s("     商品列表 onNext  " + registerModule);
                if (registerModule != null && registerModule.result.equals("SUCCESS")) {
                    if (state == 1) {
                        arrayList = registerModule.data;
                        consultationAdapter.setNewData(arrayList);
                        refreshLayout.finishRefresh();
                    } else if (state == 2) {
                        arrayList.addAll(registerModule.data);
                        consultationAdapter.setNewData(arrayList);
                        refreshLayout.finishLoadMore();
                    } else {
                        arrayList = registerModule.data;
                        consultationAdapter.setNewData(arrayList);
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
