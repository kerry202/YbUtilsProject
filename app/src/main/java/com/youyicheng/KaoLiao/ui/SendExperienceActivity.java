package com.youyicheng.KaoLiao.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.adapters.MyBaseAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.views.FlowTagView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SendExperienceActivity extends BaseActivity {
    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;

    @BindView(R.id.send_experience_rl)
    RelativeLayout send_experience_rl;

    @BindView(R.id.flag_tv)
    TextView flagTv;
    @BindView(R.id.right_arrows_tv)
    ImageView rightArrowsTv;
    @BindView(R.id.flow_layout)
    FlowTagView flowLayout;

    private MyBaseAdapter adapter;
    private boolean sx_state = true;
    private ArrayList<String> bitmaps = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.send_experience_layout;
    }

    @Override
    protected void initView() {
        flowLayout.setVisibility(View.GONE);
        rightArrowsTv.setBackgroundResource(R.mipmap.right_arrows_icon);
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#测试1#");
        bitmaps.add("#测试2#");
        bitmaps.add("#考研秘诀AAAA#");
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#秘诀#");
        adapter = new MyBaseAdapter(bitmaps);
        flowLayout.setAdapter(adapter);
        flowLayout.setItemClickListener(new FlowTagView.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                adapter.getItem(position);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.title_back, R.id.title_send, R.id.send_experience_rl})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
            case R.id.send_experience_rl:
                if (sx_state) {
                    sx_state = false;
                    rightArrowsTv.setBackgroundResource(R.mipmap.x_arrows_icon);
                    flowLayout.setVisibility(View.VISIBLE);
                } else {
                    sx_state = true;
                    flowLayout.setVisibility(View.GONE);
                    rightArrowsTv.setBackgroundResource(R.mipmap.right_arrows_icon);
                }

                break;
        }
    }

}
