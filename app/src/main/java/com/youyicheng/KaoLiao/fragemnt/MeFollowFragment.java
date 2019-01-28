package com.youyicheng.KaoLiao.fragemnt;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youyicheng.KaoLiao.adapters.FollowAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.module.MyColltionBean;
import com.youyicheng.KaoLiao.module.MyFollowBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MeFollowFragment extends BaseFragment {
    @BindView(R.id.experience_recycler)
    RecyclerView experienceRecycler;
    private FollowAdapter detailsAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.me_fragment_layout;
    }

    @Override
    protected void initView() {

    }

    List<MyFollowBean.DataBean> arrayList = new ArrayList<>();

    public void setData(List<MyFollowBean.DataBean> arrayList) {
        detailsAdapter = new FollowAdapter(getActivity(), arrayList);
        experienceRecycler.setAdapter(detailsAdapter);
    }


    @Override
    protected void initData() {

        experienceRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));


    }

}
