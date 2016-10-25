package com.dmcc.bid.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * 基类Activity
 *
 * @author wushange
 *         created at 2016/06/30 14:03
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected View mContextView = null;//view
    protected Activity mContext = null;//context
    protected final String TAG = this.getClass().getSimpleName();
    private String title;//title
    private int iconId;
    protected boolean isVisible;//是否可见
    protected boolean isPrepared;//是否初始化完成
    protected boolean isFirstLoad = true;//是否首次加载
    protected Operation mBaseOperation = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActivityManager.getActivityManager().addActivity(getActivity());// 压入栈统一管理
        Log.d(TAG, "BaseFragmentV4_-->onCreateView()");
        if (null == mContextView) {
            initParms(getArguments());
            View mView = bindView();
            if (null == mView) {
                isFirstLoad = true;
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            ButterKnife.bind(this, mContextView);//初始化黄油刀
            mBaseOperation = new Operation(getActivity());//初始化共同操作
            initView(mContextView);//初始化视图
            isPrepared = true;//初始化视图完成
            doBusiness(getActivity());//初始化操作
            lazyLoad();//执行懒加载

        }

        return mContextView;
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;

        lazyInitBusiness(getActivity());
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {
    }

    public void registEvent() {

        EventBus.getDefault().register(this);
    }

    public void unregistEvent() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4_-->onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "BaseFragmentV4_-->onSaveInstanceState()");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        Log.d(TAG, "BaseFragmentV4_-->onStart()");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "BaseFragmentV4_-->onResume()");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "BaseFragmentV4_-->onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "BaseFragmentV4_-->onStop()");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "BaseFragmentV4_-->onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragmentV4_-->onDetach()");
        super.onDetach();
        if (EventBus.getDefault().isRegistered(this)) {
            unregistEvent();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);
        }
    }


    public Activity getContext() {
        return getActivity();
    }

    public void initIconWithText(String text, int iconId) {
        this.title = text;
        this.iconId = iconId;
    }

    public String getTitle() {
        return title;

    }

    public int getIconId() {
        return iconId;
    }


}
