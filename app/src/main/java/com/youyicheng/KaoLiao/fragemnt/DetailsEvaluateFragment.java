package com.youyicheng.KaoLiao.fragemnt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yb.refrsh.SmartRefreshLayout;
import com.yb.refrsh.api.RefreshLayout;
import com.yb.refrsh.listener.OnLoadMoreListener;
import com.yb.refrsh.listener.OnRefreshListener;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.EvaluateAdapter;
import com.youyicheng.KaoLiao.base.BaseFragment;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.CommentBean;
import com.youyicheng.KaoLiao.util.Logs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsEvaluateFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.evaluate_recycler)
    RecyclerView evaluateRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.comment_count)
    TextView commentCount;
    private List<CommentBean.DataBean> dataBeanArrayList = new ArrayList<>();
    private EvaluateAdapter evaluateAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.details_evaluate_layout;
    }

    private String goods_id;

    public void getGoodsId(String goods_id) {
        this.goods_id = goods_id;
    }

    @Override
    protected void initView() {


        refreshLayout.setOnLoadMoreListener(this);
        evaluateRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        evaluateAdapter = new EvaluateAdapter(getActivity(), dataBeanArrayList);
        evaluateRecycler.setAdapter(evaluateAdapter);

    }

    @Override
    protected void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_id", goods_id + "");
        HttpUtils.getInstance().sendRequest(getActivity(), params, RequestState.STATE_DIALOG, MyInterface.commentList, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                CommentBean commentBean = new Gson().fromJson(data, CommentBean.class);
                Logs.s("     评论列表 onNext  " + commentBean);
                dataBeanArrayList = commentBean.data;

                if (dataBeanArrayList != null && dataBeanArrayList.size() > 0) {
                    commentCount.setText("研友评价 (" + dataBeanArrayList.size() + ")");
                    if (state == 0) {
                        evaluateAdapter.setNewData(dataBeanArrayList);
                    } else {
                        evaluateAdapter.addData(dataBeanArrayList);
                    }
                } else {
                    commentCount.setText("研友评价 (0)");
                }
                refreshLayout.finishLoadMore();

            }

            @Override
            public void onError(String msg) {
                Logs.s("     评论列表 onError  " + msg);
            }
        });
    }

    private int state = 0;

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        state = 1;
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }


}
