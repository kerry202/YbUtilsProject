package com.youyicheng.KaoLiao.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.youyicheng.KaoLiao.adapters.MyBaseAdapter;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.AddressAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.views.FlowTagView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SearshActivity extends BaseActivity {

    @BindView(R.id.canclr_tv)
    TextView canclrTv;
    @BindView(R.id.input_searsh_str)
    EditText inputEt;
    @BindView(R.id.home_searsh)
    RelativeLayout homeSearsh;
    @BindView(R.id.delete_searsh)
    ImageView deleteSearsh;
    @BindView(R.id.searsh_ll)
    LinearLayout searshLl;
    @BindView(R.id.clean_iv)
    ImageView clean_iv;
    @BindView(R.id.searsh_recycler)
    RecyclerView searshRecycler;
    @BindView(R.id.flow_layout)
    FlowTagView flowLayout1;
    @BindView(R.id.flow_layout2)
    FlowTagView flowLayout2;

    @BindView(R.id.searsh_rl)
    RelativeLayout searsh_rl;
    private ArrayList<String> titles = new ArrayList<>();
//    private AddressAdapter addressAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.searsh_layout;
    }

    private MyBaseAdapter adapter1;
    private MyBaseAdapter adapter2;

    private ArrayList<String> bitmaps = new ArrayList<>();

    @Override
    protected void initView() {


        bitmaps.add("#考研秘诀#");
        bitmaps.add("#测试1#");
        bitmaps.add("#考研秘诀AAAA#");
        bitmaps.add("#秘诀#");
        bitmaps.add("#秘诀#");

        adapter1 = new MyBaseAdapter(bitmaps);
        flowLayout1.setAdapter(adapter1);
        flowLayout1.setItemClickListener(new FlowTagView.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                adapter1.getItem(position);
                adapter1.notifyDataSetChanged();
            }
        });
        adapter2 = new MyBaseAdapter(bitmaps);

        bitmaps.add("2222");
        bitmaps.add("2222");
        flowLayout2.setAdapter(adapter2);
        flowLayout2.setItemClickListener(new FlowTagView.TagItemClickListener() {
            @Override
            public void itemClick(int position) {
                adapter2.getItem(position);
                adapter2.notifyDataSetChanged();
            }
        });

        searshRecycler.setLayoutManager(new LinearLayoutManager(activity));

        titles.add("考研窍门");
        titles.add("你不知道的考研窍门");
        titles.add("测试测试考研窍门");


//        addressAdapter = new AddressAdapter(titles, 1);
//
//        searshRecycler.setAdapter(addressAdapter);

        inputEt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //是否是回车键
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //搜索
                    hideEsc();
                    startActivity(new Intent(activity, SearshResultActivity.class));

                }
                return false;
            }
        });

        inputEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.length() == 0 && s != null) {
//                    clean_iv.setVisibility(View.GONE);
//                    addressAdapter.setText("");
//                    addressAdapter.notifyDataSetChanged();
//                } else {
//                    clean_iv.setVisibility(View.VISIBLE);
//                    addressAdapter.setText("" + s);
//                    addressAdapter.notifyDataSetChanged();
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        addressAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//            }
//        });

    }


    private void hideEsc() {
        /*隐藏软键盘*/
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

        }
    }

    @Override
    protected void initData() {


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread() {

    }


    @OnClick({R.id.canclr_tv, R.id.input_searsh_str, R.id.searsh_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.canclr_tv:
                String s = inputEt.getText().toString();
                if (s == null || s.length() == 0) {
                    finish();
                } else {
                    inputEt.setText("");
//                    searshRecycler.setVisibility(View.GONE);
//                    searshLl.setVisibility(View.VISIBLE);
                }

                hideEsc();
                break;

            case R.id.input_searsh_str:
//                addressAdapter.setText("");
//                searshRecycler.setVisibility(View.VISIBLE);
//                searshLl.setVisibility(View.GONE);

                break;
            case R.id.searsh_rl:

                inputEt.setText("");
//                addressAdapter.setText("");
//                addressAdapter.notifyDataSetChanged();
                break;

        }
    }
}
