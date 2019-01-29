package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.recycler.baseholder.BaseQuickAdapter;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnLoadMoreListener;
import com.yb.refrsh.listener.OnRefreshListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.ConsultationAdapter;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.adapters.MyColltionAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.LoginBean;
import com.youyicheng.KaoLiao.module.MyColltionBean;
import com.youyicheng.KaoLiao.ui.DetailsActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MeColltionFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;
    private MyColltionAdapter detailsAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_layout;
    }

    List<MyColltionBean.DataBean> arrayList = new ArrayList<>();

    @Override
    protected void initView() {

        experienceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList.add(null);
        arrayList.add(null);
        detailsAdapter = new MyColltionAdapter(getActivity(), arrayList);

        experienceRecycler.setAdapter(detailsAdapter);


        detailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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


    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    public void setData(List<MyColltionBean.DataBean> arrayList) {
        this.arrayList = arrayList;
        detailsAdapter.setNewData(arrayList);
    }
}
