package com.youyicheng.KaoLiao.fragemnt;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youyicheng.KaoLiao.adapters.FollowAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MeFollowFragment extends BaseFragment {
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

        for (int i = 0; i < 4; i++) {
            arrayList.add("");
        }

        FollowAdapter detailsAdapter = new FollowAdapter(arrayList);

        experienceRecycler.setAdapter(detailsAdapter);


    }
}
