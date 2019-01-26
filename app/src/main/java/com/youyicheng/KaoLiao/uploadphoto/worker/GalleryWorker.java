package com.youyicheng.KaoLiao.uploadphoto.worker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.youyicheng.KaoLiao.uploadphoto.FactoryHelperActivity;
import com.youyicheng.KaoLiao.uploadphoto.base.BaseWorker;
import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.result.ResultData;


/**
 * Created by anlia on 2018/5/16.
 */

public class GalleryWorker extends BaseWorker {
    public GalleryWorker(Context context, String photoDir, String photoName) {
        super(context,photoDir,photoName);
    }

    @Override
    public void StartForResult(@NonNull final PhotoFactory.OnResultListener listener) {
        FactoryHelperActivity.selectPhotoFromGallery(mContext, new FactoryHelperActivity.ActivityResultListener() {
            @Override
            public void onResultCallback(int requestCode, int resultCode, Intent data) {
                if(data == null){
                    listener.OnCancel();
                }else {
                    mUri = data.getData();
                    listener.OnSuccess(new ResultData(mContext,mUri,requestCode,resultCode,data,PhotoFactory.CODE_SUCCESS));
                }
            }
        });
    }
}
