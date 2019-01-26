package com.youyicheng.KaoLiao.fragemnt;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.recycler.baseholder.BaseQuickAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.ConsultationAdapter;
import com.youyicheng.KaoLiao.adapters.DetailsAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.ui.DetailsActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class MeColltionFragment extends BaseFragment {
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

        for (int i = 0; i < 10; i++) {
            arrayList.add("");
        }

        DetailsAdapter detailsAdapter = new DetailsAdapter(arrayList);

        experienceRecycler.setAdapter(detailsAdapter);


        detailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("titleName", "备考资料");
                startActivity(intent);
            }
        });

    }
}
