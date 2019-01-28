package com.youyicheng.KaoLiao.uploadphoto.base;

import android.content.Context;
import android.net.Uri;

import com.youyicheng.KaoLiao.uploadphoto.factory.PhotoFactory;
import com.youyicheng.KaoLiao.uploadphoto.utils.UriUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public abstract class BaseWorker {
    protected Uri mUri;
    protected Map<String,Object> mMap;
    protected Context mContext;
    protected String mPhotoDir;
    protected String mPhotoName;

    public BaseWorker(Context context, String photoDir, String photoName){
        mContext = context;
        mPhotoDir = photoDir;
        mPhotoName = photoName;

        mUri = UriUtils.GetFileUri(context,new File(mPhotoDir, mPhotoName));
        mMap = new HashMap<>();
    }

    public abstract void StartForResult(PhotoFactory.OnResultListener listener);
}
