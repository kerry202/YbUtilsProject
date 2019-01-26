package com.youyicheng.KaoLiao.permission.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.youyicheng.KaoLiao.permission.PermissionFail;
import com.youyicheng.KaoLiao.permission.PermissionSuccess;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


final public class Utils {
  private Utils(){}

  public static boolean isOverMarshmallow() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }

  @TargetApi(value = Build.VERSION_CODES.M)
  public static List<String> findDeniedPermissions(Context activity, String... permission){
    List<String> denyPermissions = new ArrayList<>();
    for(String value : permission){
      if(activity.checkSelfPermission(value) != PackageManager.PERMISSION_GRANTED){
        denyPermissions.add(value);
      }
    }
    return denyPermissions;
  }

  public static List<Method> findAnnotationMethods(Class clazz, Class<? extends Annotation> clazz1){
    List<Method> methods = new ArrayList<>();
    for(Method method : clazz.getDeclaredMethods()){
      if(method.isAnnotationPresent(clazz1)){
        methods.add(method);
      }
    }
    return methods;
  }

  public static <A extends Annotation> Method findMethodPermissionFailWithRequestCode(Class clazz,
      Class<A> permissionFailClass, int requestCode) {
    for(Method method : clazz.getDeclaredMethods()){
      if(method.isAnnotationPresent(permissionFailClass)){
        if(requestCode == method.getAnnotation(PermissionFail.class).requestCode()){
          return method;
        }
      }
    }
    return null;
  }

  public static boolean isEqualRequestCodeFromAnntation(Method m, Class clazz, int requestCode){
    if(clazz.equals(PermissionFail.class)){
      return requestCode == m.getAnnotation(PermissionFail.class).requestCode();
    } else if(clazz.equals(PermissionSuccess.class)){
      return requestCode == m.getAnnotation(PermissionSuccess.class).requestCode();
    } else {
      return false;
    }
  }

  public static <A extends Annotation> Method findMethodWithRequestCode(Class clazz,
      Class<A> annotation, int requestCode) {
    for(Method method : clazz.getDeclaredMethods()){
      if(method.isAnnotationPresent(annotation)){
        if(isEqualRequestCodeFromAnntation(method, annotation, requestCode)){
          return method;
        }
      }
    }
    return null;
  }

  public static <A extends Annotation> Method findMethodPermissionSuccessWithRequestCode(Class clazz,
      Class<A> permissionFailClass, int requestCode) {
    for(Method method : clazz.getDeclaredMethods()){
      if(method.isAnnotationPresent(permissionFailClass)){
        if(requestCode == method.getAnnotation(PermissionSuccess.class).requestCode()){
          return method;
        }
      }
    }
    return null;
  }

  public static Activity getActivity(Object object){
    if(object instanceof Fragment){
      return ((Fragment)object).getActivity();
    } else if(object instanceof Activity){
      return (Activity) object;
    }
    return null;
  }
}
