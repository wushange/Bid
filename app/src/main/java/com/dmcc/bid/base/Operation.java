package com.dmcc.bid.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.dmcc.bid.MyApplication;
import com.dmcc.bid.R;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.io.Serializable;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 基本的操作共通抽取
 * Created by wushange on  2016/06/30.
 */
public class Operation {
    /***
     * Activity之间数据传输数据对象Key
     **/
    public final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";
    /**
     * 激活Activity组件意图
     **/
    private Intent mIntent = new Intent();
    /***
     * 上下文
     **/
    private Activity mContext = null;
    /***
     * 整个应用Applicaiton
     **/
    private MyApplication mApplication = null;
    private SweetAlertDialog mSweetAlertDialog;
    private boolean isShow = true;
    private Toast mToast;

    public Operation(Activity mContext) {
        this.mContext = mContext;
        mApplication = (MyApplication) this.mContext.getApplicationContext();
    }

    /**
     * 跳转Activity
     *
     * @param activity 需要跳转至的Activity
     */
    public void forward(Class<? extends Activity> activity) {
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent);
    }

    /**
     * 跳转Activity
     *
     * @param activity 需要跳转至的Activity
     */
    public void forward(Class<? extends Activity> activity, Bundle bundle) {
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent, bundle);
    }

    /**
     * 跳转Activity
     *
     * @param activity 需要跳转至的Activity
     */
    public void forwardAndFinish(Class<? extends Activity> activity) {
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent);
        mContext.finish();
    }

    /**
     * 跳转Activity
     *
     * @param activity 需要跳转至的Activity
     */
    public void forwardAndFinish(Class<? extends Activity> activity, Bundle bundle) {
        mIntent.setClass(mContext, activity);
        mContext.startActivity(mIntent, bundle);
        mContext.finish();
    }


    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, Bundle value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, Serializable value) {
        mIntent.putExtra(key, value);
    }

    /**
     * 设置传递参数
     *
     * @param key   参数key
     * @param value 数据传输对象
     */
    public void addParameter(String key, String value) {
        mIntent.putExtra(key, value);
    }


    /***
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * *
     * ToastUtil                                                   *
     * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public void showToast(CharSequence text) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void EmptyToast() {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "不允许为空！", Toast.LENGTH_SHORT);
        } else {
            mToast.setText("不允许为空！");
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public void showToastInCenter(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }


    /***
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * *
     * SweetAlertDialog                                            *
     * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    public void showSweetBasic(String str) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext);
        mSweetAlertDialog.setTitleText(str);
        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.show();
    }

    public void showSweetSub(String str, String subStr) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext);
        mSweetAlertDialog.setTitleText(str);
        mSweetAlertDialog.setCancelable(true);
        mSweetAlertDialog.setContentText(subStr);
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.show();
    }

    public void showSweetLoading(boolean cancel) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(mContext.getResources().getString(R.string.loading));
        mSweetAlertDialog.show();
        mSweetAlertDialog.setCancelable(cancel);
    }

    public void showSweetLoading(String str) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(str);
        mSweetAlertDialog.show();
        mSweetAlertDialog.setCancelable(false);
    }

    public void showSweetLoadingCanCancel(String str) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE)
                .setTitleText(str);
        mSweetAlertDialog.show();
        mSweetAlertDialog.setCancelable(true);
    }

    public void showSweetSuccess(String title, String content) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.show();
    }

    public void showSweetSuccess(String title, String content, SweetAlertDialog.OnSweetClickListener successCallBack) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.setConfirmClickListener(successCallBack);
        mSweetAlertDialog.show();
    }

    public void showSweetError(String title, String content) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.show();
    }

    public void showSweetWarning(String title, String content) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.show();
    }

    public void showSweet2Btn(String title, String content, String canceltext, String confirmText, SweetAlertDialog.OnSweetClickListener cancelCallback, SweetAlertDialog.OnSweetClickListener confirmCallback) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.setCancelText(canceltext);
        mSweetAlertDialog.setConfirmText(confirmText);
        mSweetAlertDialog.showCancelButton(true);
        mSweetAlertDialog.setCancelClickListener(cancelCallback);
        mSweetAlertDialog.setConfirmClickListener(confirmCallback);
        mSweetAlertDialog.show();
    }

    public void showSweetCustom(String title, String content, int resId) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        mSweetAlertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        mSweetAlertDialog.setTitleText(title);
        mSweetAlertDialog.setContentText(content);
        mSweetAlertDialog.setCustomImage(resId);
        mSweetAlertDialog.show();
    }

    public SweetAlertDialog getmSweetAlertDialog(int type) {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
            mSweetAlertDialog.dismissWithAnimation();
        return mSweetAlertDialog = new SweetAlertDialog(mContext, type);
    }

    public void dissLoading() {
        if (!mContext.isFinishing() && mSweetAlertDialog != null && mSweetAlertDialog.isShowing()) {
            mSweetAlertDialog.dismissWithAnimation();
        }

    }

    /***
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     * *
     * 权限检测                                                     *
     * *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */

    public void checkPermission(Context context, AcpListener listener, String... permissions) {
        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(permissions).build(), listener);
    }

    public void checkPermissionOnlyDendied(Context context, AcpListener listener, String deniedMessage, String... permissions) {


        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(permissions).setDeniedMessage(deniedMessage).build(), listener);

    }


    public void checkPermissionShouldShow(Context context, AcpListener listener, String rationnaleMsg,
                                          String deniedMessage, String... permissions) {
        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(permissions)
                .setDeniedMessage(deniedMessage)
                .setRationalMessage(rationnaleMsg)
                .setRationalBtn("好的")
                .setDeniedCloseBtn("不用了")
                .setDeniedSettingBtn("去设置").build(), listener);
    }

    public void checkPermissionShouldShow(Context context, AcpListener listener, String rationnaleMsg, String rationaleBtnMsg,
                                          String deniedMessage, String deniedBtnCloseMsg, String deniedBtnSettingMsg, String... permissions) {


        Acp.getInstance(context).request(new AcpOptions.Builder().setPermissions(permissions)
                .setDeniedMessage(deniedMessage)
                .setRationalMessage(rationnaleMsg)
                .setRationalBtn(rationaleBtnMsg)
                .setDeniedCloseBtn(deniedBtnCloseMsg)
                .setDeniedSettingBtn(deniedBtnSettingMsg).build(), listener);


    }

}
