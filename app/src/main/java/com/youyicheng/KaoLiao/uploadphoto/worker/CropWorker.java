package com.youyicheng.KaoLiao.uploadphoto.worker;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;


import com.youyicheng.KaoLiao.uploadphoto.FactoryHelperActivity;
import com.youyicheng.KaoLiao.uploadphoto.base.BaseWorker;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;
import com.youyicheng.KaoLiao.uploadphoto.utils.UriUtils;

import java.io.File;

/**
 * Created by anlia on 2018/5/16.
 */

public class CropWorker extends BaseWorker {
    private Uri cropData;

    public CropWorker(Context context, String photoDir, String photoName, Uri data) {
        super(context,photoDir,photoName);
        cropData = UriUtils.GetUriForCrop(mContext,data);
        mMap.put(MediaStore.EXTRA_OUTPUT,Uri.fromFile(new File(photoDir,photoName)));//缓存裁剪图片得用这种格式的uri
        mMap.put("DataAndType",cropData);
    }

    public CropWorker AddAspectX(int value){
        mMap.put("aspectX",value);
        return this;
    }

    public CropWorker AddAspectY(int value){
        mMap.put("aspectY",value);
        return this;
    }

    @Override
    public void StartForResult(@NonNull final PhotoFactory.OnResultListener listener) {
        FactoryHelperActivity.cropPhoto(mContext, mMap, new FactoryHelperActivity.ActivityResultListener() {
            @Override
            public void onResultCallback(int requestCode, int resultCode, Intent data) {
                if(data == null){
                    listener.OnCancel();
                }else {
                    listener.OnSuccess(new ResultData(mContext,mUri,requestCode,resultCode,data,PhotoFactory.CODE_SUCCESS));
                }
            }
        });
    }
}
