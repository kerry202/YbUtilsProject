package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recycler.baseholder.BaseQuickAdapter;
import com.yb.refrsh.api.RefreshLayout;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.adapters.OrderAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.OrderDetailsActivity;
import com.youyicheng.KaoLiao.util.Logs;

import java.util.ArrayList;

import butterknife.BindView;

public class MeOrderFragment extends BaseFragment {
    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {


        experienceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            arrayList.add("");
        }

        OrderAdapter detailsAdapter = new OrderAdapter(arrayList);

        experienceRecycler.setAdapter(detailsAdapter);

        detailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(),OrderDetailsActivity.class));
            }
        });


    }

}
