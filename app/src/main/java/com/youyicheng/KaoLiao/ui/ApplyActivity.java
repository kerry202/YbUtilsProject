package com.youyicheng.KaoLiao.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.AddressAdapter;
import com.youyicheng.KaoLiao.adapters.CityAdapter;
import com.youyicheng.KaoLiao.adapters.MajorAdapter;
import com.youyicheng.KaoLiao.adapters.SchoolAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.AddressBean;
import com.youyicheng.KaoLiao.module.CityBean;
import com.youyicheng.KaoLiao.module.MajorBean;
import com.youyicheng.KaoLiao.module.SchoolBean;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;
import com.youyicheng.KaoLiao.util.BitmapCompressionUtils;
import com.youyicheng.KaoLiao.util.LQRPhotoSelectUtils;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyActivity extends BaseActivity {


    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.input_class_et)
    TextView inputClassEt;
    @BindView(R.id.input_major_et)
    TextView inputMajorEt;
    @BindView(R.id.input_province_et)
    TextView inputProvinceEt;
    @BindView(R.id.input_school_et)
    TextView inputSchoolEt;
    @BindView(R.id.select_yes)
    TextView selectYes;
    @BindView(R.id.select_no)
    TextView selectNo;
    @BindView(R.id.input_rx_time_et)
    EditText inputRxTimeEt;
    @BindView(R.id.upload_zheng_rl)
    RelativeLayout uploadZhengRl;
    @BindView(R.id.upload_fan_rl)
    RelativeLayout uploadFanRl;
    @BindView(R.id.get_men_class_rl)
    LinearLayout getMenClassRl;
    @BindView(R.id.get_zy_rl)
    RelativeLayout getZyRl;
    @BindView(R.id.get_city_rl)
    RelativeLayout getCityRl;
    @BindView(R.id.get_school_rl)
    RelativeLayout getSchoolRl;
    @BindView(R.id.in_time_ll)
    LinearLayout inTimeLl;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    private String category_id = "";
    private String major_id = "";
    private String city_id = "";
    private String school_id = "";
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    Uri outputUri1 = null;//上传照片路径地址

    PhotoFactory photoFactory = new PhotoFactory(this);


    @Override
    protected int getLayoutId() {
        return R.layout.apply_layout;
    }

    @Override
    protected void initView() {
        titleSend.setText("提交");
        titleName.setText("学长申请");

        inputRxTimeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() == 4) {
                    int i = Integer.parseInt(s + "");
                    if (i >= 2000 && i <= 2100) {

                    } else {
                        ToastUtil.show(activity, "入学日期有误");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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


    @Subscribe
    public void onEvent(MyEvents event) {

        Logs.s("   收到消息:   " + event.errmsg);

    }

    private boolean select = true;

    @OnClick({R.id.title_back, R.id.title_send, R.id.get_men_class_rl, R.id.get_zy_rl, R.id.get_city_rl, R.id.get_school_rl, R.id.select_yes, R.id.select_no, R.id.upload_zheng_rl, R.id.upload_fan_rl})
    public void onViewClicked(View view) {

        Drawable redDrawable = activity.getResources().getDrawable(R.mipmap.red_circle_icon);
        Drawable whiteDrawable = activity.getResources().getDrawable(R.mipmap.white_circle_icon);
        redDrawable.setBounds(0, 0, 56, 56);
        whiteDrawable.setBounds(0, 0, 56, 56);

        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
            case R.id.select_yes:

                select = true;
                selectYes.setCompoundDrawables(redDrawable, null, null, null);
                selectYes.setCompoundDrawablePadding(20);

                selectNo.setCompoundDrawables(whiteDrawable, null, null, null);
                selectNo.setCompoundDrawablePadding(20);

                break;
            case R.id.select_no:

                select = false;
                selectYes.setCompoundDrawables(whiteDrawable, null, null, null);
                selectYes.setCompoundDrawablePadding(10);

                selectNo.setCompoundDrawables(redDrawable, null, null, null);
                selectNo.setCompoundDrawablePadding(10);

                break;
            case R.id.upload_zheng_rl:
                uploadPhoto();
                break;
            case R.id.upload_fan_rl:
                uploadPhoto();
                break;
            case R.id.get_men_class_rl:
                myStartActivity(1, "门类");//门类
                break;
            case R.id.get_zy_rl:
                if (category_id == null || category_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择门类");
                    return;
                }
                myStartActivity(2, "专业");//专业

                break;
            case R.id.get_city_rl:

                if (category_id == null || category_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择门类");
                    return;
                }
                if (major_id == null || major_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择专业");
                    return;
                }

                Logs.s("   省份 category_id   " + category_id);
                Logs.s("   省份  major_id   " + major_id);
                myStartActivity(3, "省份");//城市

                break;
            case R.id.get_school_rl:

                if (category_id == null || category_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择门类");
                    return;
                }
                if (major_id == null || major_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择专业");
                    return;
                }
                if (city_id == null || city_id.length() == 0) {
                    ToastUtil.show(activity, "请先选择省份");
                    return;
                }

                myStartActivity(4, "学校");//学校

                break;
        }
    }

    private void uploadPhoto() {

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

                            upload1(file);

                            Logs.s("     上传图片 file  " + resultData);

                        } catch (Exception e) {

                        }
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String category_name = data.getStringExtra("category_name");
                    category_id = data.getStringExtra("category_id");
                    inputClassEt.setText(category_name);
                    inputMajorEt.setText("");
                    inputProvinceEt.setText("");
                    inputSchoolEt.setText("");
                    major_id = "";
                    city_id = "";
                    school_id = "";
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    String major_name = data.getStringExtra("major_name");
                    major_id = data.getStringExtra("major_id");
                    inputMajorEt.setText(major_name);
                    inputProvinceEt.setText("");
                    inputSchoolEt.setText("");
                    city_id = "";
                    school_id = "";
                }
                break;
            case 3:
                if (resultCode == RESULT_OK) {
                    String city_name = data.getStringExtra("city_name");
                    city_id = data.getStringExtra("city_id");
                    inputProvinceEt.setText(city_name);
                    inputSchoolEt.setText("");
                    school_id = "";
                }
                break;
            case 4:
                if (resultCode == RESULT_OK) {
                    String school_name = data.getStringExtra("school_name");
                    school_id = data.getStringExtra("school_id");
                    inputSchoolEt.setText(school_name);
                }
                break;
        }
    }

    private void upload1(File file) {

        HttpUtils.getInstance().sendPhoto(activity, file, RequestState.STATE_DIALOG, MyInterface.uploadPhoto, new OnDataListener() {
            @Override
            public void onSuccess(String data) {

                Logs.s("     上传图片 onNext  " + data);

            }

            @Override
            public void onError(String msg) {

                Logs.s("     上传图片 onError  " + msg);

            }
        });

    }

    private void myStartActivity(int state, String titleName) {

        Intent intent = new Intent(activity, SelectAddressActivity.class);
        intent.putExtra("state", state);
        intent.putExtra("titleName", titleName);
        intent.putExtra("category_id", category_id);
        intent.putExtra("major_id", major_id);
        intent.putExtra("city_id", city_id);
        startActivityForResult(intent, state);

    }
}
