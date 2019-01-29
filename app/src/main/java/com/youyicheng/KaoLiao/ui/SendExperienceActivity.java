package com.youyicheng.KaoLiao.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.MyBaseAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.LoginBean;
import com.youyicheng.KaoLiao.module.PhotoBean;
import com.youyicheng.KaoLiao.module.SendBean;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;
import com.youyicheng.KaoLiao.util.BitmapCompressionUtils;
import com.youyicheng.KaoLiao.util.HomeEvents;
import com.youyicheng.KaoLiao.util.LQRPhotoSelectUtils;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @BindView(R.id.upload1)
    ImageView upload1;

    @BindView(R.id.upload2)
    ImageView upload2;

    @BindView(R.id.right_arrows_tv)
    ImageView rightArrowsTv;
    @BindView(R.id.load_map)
    RelativeLayout loadMap;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.des)
    EditText des;
//    @BindView(R.id.flow_layout)
//    FlowTagView flowLayout;

    private MyBaseAdapter adapter;
    private boolean sx_state = true;
    private ArrayList<String> bitmaps = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.send_experience_layout;
    }

    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    Uri outputUri1 = null;//上传照片路径地址

    PhotoFactory photoFactory = new PhotoFactory(this);

    private void uploadPhoto() {

        photoFactory.FromGallery()
                .StartForResult(new PhotoFactory.OnResultListener() {

                    @Override
                    public void OnCancel() {

                    }

                    @Override
                    public void OnSuccess(ResultData resultData) {
                        try {

                            Bitmap bitmap = resultData.GetBitmap();
                            File file = BitmapCompressionUtils.compressImage(bitmap);

                            upload1(file);


                        } catch (Exception e) {

                        }
                    }
                });
    }

    private String url;

    private void upload1(File file) {

        HttpUtils.getInstance().sendPhoto(activity, file, RequestState.STATE_DIALOG, MyInterface.uploadPhoto, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                PhotoBean photoBean = new Gson().fromJson(data, PhotoBean.class);

                upload2.setVisibility(View.GONE);
                upload1.setVisibility(View.VISIBLE);

                Glide.with(activity)
                        .load(photoBean.url)
                        .into(upload1);
                url = photoBean.url;

                Logs.s("     上传图片 onNext  " + photoBean);

            }

            @Override
            public void onError(String msg) {

                Logs.s("     上传图片 onError  " + msg);

            }
        });

    }

    private void init() {

        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                // 4、当拍照或从图库选取图片成功后回调
                outputUri1 = outputUri;
                try {
                    FileInputStream fis = new FileInputStream(outputFile);
                    Bitmap bitmap = BitmapFactory.decodeStream(fis);

                    File file = BitmapCompressionUtils.compressImage(bitmap);
                    ArrayList<File> arrayList = new ArrayList<>();
                    arrayList.add(file);

                } catch (Exception e) {

                }

            }
        }, false);
    }


    @Override
    protected void initView() {
//        flowLayout.setVisibility(View.GONE);
        rightArrowsTv.setBackgroundResource(R.mipmap.right_arrows_icon);
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#测试1#");
        bitmaps.add("#测试2#");
        bitmaps.add("#考研秘诀AAAA#");
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#秘诀#");
        adapter = new MyBaseAdapter(bitmaps);
//        flowLayout.setAdapter(adapter);
//        flowLayout.setItemClickListener(new FlowTagView.TagItemClickListener() {
//            @Override
//            public void itemClick(int position) {
//                adapter.getItem(position);
//                adapter.notifyDataSetChanged();
//            }
//        });
        init();
        //6.0以上动态获取权限  申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.title_back, R.id.load_map, R.id.title_send, R.id.send_experience_rl})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                send();
                break;
            case R.id.load_map:
                uploadPhoto();
                break;
            case R.id.send_experience_rl:
                if (sx_state) {
                    sx_state = false;
                    rightArrowsTv.setBackgroundResource(R.mipmap.x_arrows_icon);
//                    flowLayout.setVisibility(View.VISIBLE);
                } else {
                    sx_state = true;
//                    flowLayout.setVisibility(View.GONE);
                    rightArrowsTv.setBackgroundResource(R.mipmap.right_arrows_icon);
                }

                break;
        }
    }

    private void send() {
        HashMap<String, String> params = new HashMap<>();
        params.put("goods_type", "0");
        params.put("goods_name", title.getText().toString());
        params.put("goods_img", url);
        params.put("intro", des.getText().toString());
        params.put("content", "aaaaa");
        params.put("tags_id", "1");


        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.sendGoods, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                Logs.s("     发送 onNext  " + data);
                SendBean sendBean = new Gson().fromJson(data, SendBean.class);
                if (sendBean.result.equals("SUCCESS")) {
                    finish();
                } else {

                    ToastUtil.show(activity, "" + sendBean.message);
                }
            }

            @Override
            public void onError(String msg) {
                Logs.s("     发送 onError  " + msg);


            }
        });

    }

}
