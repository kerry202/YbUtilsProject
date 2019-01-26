package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.recycler.baseholder.BaseQuickAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.ExperienceAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.ExperienceActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ExperienceFragment extends BaseFragment {
    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_experience_layout;
    }



    @Override
    protected void initView() {
        experienceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            arrayList.add("");
        }
        ExperienceAdapter experienceAdapter = new ExperienceAdapter(arrayList);

        experienceRecycler.setAdapter(experienceAdapter);

        experienceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(), ExperienceActivity.class));
            }
        });

    }

    @Override
    protected void initData() {

    }


}
