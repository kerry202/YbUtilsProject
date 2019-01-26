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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.MyBaseAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.permission.PermissionGen;
import com.youyicheng.KaoLiao.permission.PermissionSuccess;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;
import com.youyicheng.KaoLiao.util.BitmapCompressionUtils;
import com.youyicheng.KaoLiao.util.LQRPhotoSelectUtils;
import com.youyicheng.KaoLiao.views.FlowTagView;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
    @BindView(R.id.upload_photo_recycler)
    RecyclerView uploadPhotoRecycler;
    @BindView(R.id.flow_layout)
    FlowTagView flowLayout;
    @BindView(R.id.get_pay)
    TextView getPay;
    @BindView(R.id.flag_tv)
    TextView flagTv;
    @BindView(R.id.right_arrows_tv)
    ImageView rightArrowsTv;
    @BindView(R.id.by_tv)
    TextView byTv;
    private MyBaseAdapter adapter;

    private ArrayList<String> bitmaps = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.send_goods_layout;
    }

    @Override
    protected void initView() {

        uploadPhotoRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#测试1#");
        bitmaps.add("#测试2#");
        bitmaps.add("#考研秘诀AAAA#");
        bitmaps.add("#考研秘诀#");
        bitmaps.add("#秘诀#");
        LoadMapAdapter loadMapAdapter = new LoadMapAdapter(bitmaps);

        uploadPhotoRecycler.setAdapter(loadMapAdapter);


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


    @OnClick({R.id.title_back, R.id.title_send, R.id.get_pay, R.id.by_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
            case R.id.get_pay:
                break;
            case R.id.by_tv:
                break;
        }
    }


    public class LoadMapAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        private List<String> data;

        public LoadMapAdapter(@Nullable List<String> data) {
            super(R.layout.load_map_adapter_layout, data);
            this.data = data;
        }

        @Override
        protected void convert(final BaseViewHolder helper, String item) {
            int adapterPosition = helper.getAdapterPosition();

            RelativeLayout load_map = helper.getView(R.id.load_map);
            RelativeLayout photo_rl = helper.getView(R.id.photo_rl);
            ImageView map_close_iv = helper.getView(R.id.map_close_iv);
            ImageView photo_iv = helper.getView(R.id.photo_iv);

            if (data.size() - 1 == adapterPosition) {
                photo_rl.setVisibility(View.GONE);
                map_close_iv.setVisibility(View.GONE);
                load_map.setVisibility(View.VISIBLE);
            } else {
                load_map.setVisibility(View.GONE);
                map_close_iv.setVisibility(View.VISIBLE);
                photo_rl.setVisibility(View.VISIBLE);
                photo_iv.setBackgroundResource(R.mipmap.test_icon);
            }

            map_close_iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(helper.getAdapterPosition());
                    notifyDataSetChanged();
                }
            });

            load_map.setOnClickListener(new View.OnClickListener() {
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
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
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


    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    Uri outputUri1 = null;//上传照片路径地址

    PhotoFactory photoFactory = new PhotoFactory(this);


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }


    private void showDialog() {

        init();
        //6.0以上动态获取权限  申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }


        View view = LayoutInflater.from(this).inflate(R.layout.show_add_photo_dialog, null);
        final Dialog dialog = new AlertDialog.Builder(this, R.style.add_photo_dialog)
                .setView(view)
                .setCancelable(true)
                .create();
        dialog.show();

        Window win = dialog.getWindow();
        win.setGravity(Gravity.BOTTOM);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);


        view.findViewById(R.id.camera_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //相机
                PermissionGen.with(activity)
                        .addRequestCode(LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                        ).request();
            }
        });
        view.findViewById(R.id.photo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //相册
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

                                } catch (Exception e) {
                                }
                            }
                        });
            }
        });
        view.findViewById(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
