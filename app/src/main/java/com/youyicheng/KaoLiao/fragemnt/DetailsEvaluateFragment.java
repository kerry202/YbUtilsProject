package com.youyicheng.KaoLiao.fragemnt;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.EvaluateAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class DetailsEvaluateFragment extends BaseFragment {
    @BindView(R.id.evaluate_recycler)
    RecyclerView evaluateRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.details_evaluate_layout;
    }



    @Override
    protected void initView() {

        evaluateRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            list.add("");
        }
        EvaluateAdapter evaluateAdapter = new EvaluateAdapter(list);
        evaluateRecycler.setAdapter(evaluateAdapter);


    }

    @Override
    protected void initData() {

    }

}
