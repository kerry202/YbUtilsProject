package com.youyicheng.KaoLiao.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.MyBaseAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.PhotoBean;
import com.youyicheng.KaoLiao.permission.PermissionGen;
import com.youyicheng.KaoLiao.permission.PermissionSuccess;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;
import com.youyicheng.KaoLiao.util.BitmapCompressionUtils;
import com.youyicheng.KaoLiao.util.GlideEngine;
import com.youyicheng.KaoLiao.util.LQRPhotoSelectUtils;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.ToastUtil;
import com.youyicheng.KaoLiao.views.FlowTagView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendGoodsActivity extends BaseActivity {


    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.title)
    EditText title;
    @BindView(R.id.des)
    EditText des;
    @BindView(R.id.upload_photo_recycler)
    RecyclerView uploadPhotoRecycler;
    @BindView(R.id.price)
    EditText price;
    @BindView(R.id.get_pay)
    TextView getPay;
    @BindView(R.id.by_tv)
    TextView byTv;
    @BindView(R.id.flag_tv)
    TextView flagTv;
    @BindView(R.id.right_arrows_tv)
    ImageView rightArrowsTv;

//    @BindView(R.id.flow_layout)
//    FlowTagView flowLayout;

//    private MyBaseAdapter adapter;

    private ArrayList<String> bitmaps = new ArrayList<>();
    private LoadMapAdapter loadMapAdapter;


    @Override
    protected void initView() {

        uploadPhotoRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        bitmaps.add(null);
        loadMapAdapter = new LoadMapAdapter(bitmaps);

        uploadPhotoRecycler.setAdapter(loadMapAdapter);

    }


    private void send() {

        HashMap<String, Object> params = new HashMap<>();
        params.put("goods_type", "1");
        params.put("goods_name", title.getText().toString());
        params.put("goods_img", bitmaps.get(0));
        params.put("intro", des.getText().toString());
        params.put("content", "CDC的程度");
        params.put("tags_id", "1");
        params.put("price", price.getText().toString());
        params.put("postage_type", "" + tag);

        String[] array = new String[bitmaps.size()];
        for (int i = 0; i < bitmaps.size(); i++) {
            array[i] = bitmaps.get(i);
        }
        params.put("slide_img", array);

        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.sendGoods, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     发送 onNext  " + data);

            }

            @Override
            public void onError(String msg) {
                Logs.s("     发送 onError  " + msg);

            }
        });

    }

    @Override
    protected void initData() {


    }


    public class LoadMapAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private List<String> data;

        public LoadMapAdapter(@Nullable List<String> data) {
            super(R.layout.load_map_adapter_layout, data);
            this.data = data;
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {

            ImageView add = helper.getView(R.id.add);
            RelativeLayout photo_rl = helper.getView(R.id.photo_rl);
            ImageView map_close_iv = helper.getView(R.id.map_close_iv);
            ImageView photo_iv = helper.getView(R.id.photo_iv);

            if (item == null) {
                photo_iv.setVisibility(View.GONE);
                map_close_iv.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
            } else {
                add.setVisibility(View.GONE);
                map_close_iv.setVisibility(View.VISIBLE);
                photo_iv.setVisibility(View.VISIBLE);
                Glide.with(activity)
                        .load(item)
                        .into(photo_iv);
            }


            map_close_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(helper.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            photo_rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            //相机或相册回调
            if (requestCode == 101) {
                //返回对象集合：如果你需要了解图片的宽、高、大小、用户是否选中原图选项等信息，可以用这个
//                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);

                //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
                ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
                //返回图片地址集合时如果你需要知道用户选择图片时是否选择了原图选项，用如下方法获取
//                boolean selectedOriginal = data.getBooleanExtra(EasyPhotos.RESULT_SELECTED_ORIGINAL, false);

                Bitmap bitmapFormUri = null;

                bitmaps.clear();
                for (String resultPath : resultPaths) {
                    Uri pa = Uri.fromFile(new File(resultPath));//根据路径转化为uri
                    try {
                        bitmapFormUri = BitmapCompressionUtils.getBitmapFormUri(activity, pa);
                        File file = BitmapCompressionUtils.compressImage(bitmapFormUri);
                        upload(file);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return;
            }

        } else if (RESULT_CANCELED == resultCode) {

        }
    }


    private void upload(File file) {

        HttpUtils.getInstance().sendPhoto(activity, file, RequestState.STATE_DIALOG, MyInterface.uploadPhoto, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                PhotoBean photoBean = new Gson().fromJson(data, PhotoBean.class);
                bitmaps.add(photoBean.url);
                Logs.s("     上传图片 onNext  " + photoBean);
                loadMapAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(String msg) {

                Logs.s("     上传图片 onError  " + msg);

            }
        });

    }

    private ArrayList<Photo> selectedPhotoList = new ArrayList<>();

    private void showDialog() {

        //6.0以上动态获取权限  申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }

        EasyPhotos.createAlbum(this, false, GlideEngine.getInstance())
                .setCount(8)
                .start(101);

    }

    private int tag = 0;

    @OnClick({R.id.title_back, R.id.by_tv, R.id.get_pay, R.id.title_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.by_tv:
                tag = 0;
                byTv.setBackgroundResource(R.mipmap.pay_icon);
                byTv.setTextColor(activity.getResources().getColor(R.color.price_red));
                getPay.setTextColor(activity.getResources().getColor(R.color.gray_333));
                getPay.setBackgroundResource(R.drawable.circle_gray_f6);
                break;
            case R.id.get_pay:
                tag = 1;
                getPay.setTextColor(activity.getResources().getColor(R.color.price_red));
                byTv.setTextColor(activity.getResources().getColor(R.color.gray_333));
                getPay.setBackgroundResource(R.mipmap.pay_icon);
                byTv.setBackgroundResource(R.drawable.circle_gray_f6);
                break;
            case R.id.title_send:

                if (title.getText().toString().length() == 0) {
                    ToastUtil.show(activity, "标题不能为空");
                    return;
                }
                if (des.getText().toString().length() == 0) {
                    ToastUtil.show(activity, "简介不能为空");
                    return;
                }
                if (price.getText().toString().length() == 0) {
                    ToastUtil.show(activity, "价格不能为空");
                    return;
                }
                send();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.send_goods_layout;
    }


}
