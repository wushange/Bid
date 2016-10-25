package com.dmcc.bid.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dmcc.bid.injector.HasComponent;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * 基类Activity
 *
 * @author wushange
 *         created at 2016/06/30 14:03
 */
public abstract class BaseFragmentV4 extends Fragment implements IBaseFragment {
    protected View mContextView = null;
    protected Activity mContext = null;
    protected final String TAG = this.getClass().getSimpleName();
    private String title;
    private int iconId;
    protected boolean isVisible;
    protected boolean isPrepared;
    protected boolean isFirstLoad = true;
    protected Operation mBaseOperation = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "BaseFragmentV4_-->onCreateView()");
        initInjector();
        if (null == mContextView) {
            initParms(getArguments());
            View mView = bindView();
            if (null == mView) {
                isFirstLoad = true;
                mContextView = inflater.inflate(bindLayout(), container, false);
            } else {
                mContextView = mView;
            }
            ButterKnife.bind(this, mContextView);
            mBaseOperation = new Operation(getActivity());
            initView(mContextView);
            isPrepared = true;
            doBusiness(getActivity());
            lazyLoad();

        }

        ActivityManager.getActivityManager().addActivity(getActivity());
        // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
        return mContextView;
    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;

        lazyInitBusiness(getActivity());
    }

    public abstract void initInjector();

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
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
        if (EventBus.getDefault().isRegistered(this)) {
            unregistEvent();
        }
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "BaseFragmentV4_-->onDetach()");
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mContextView != null && mContextView.getParent() != null) {
            ((ViewGroup) mContextView.getParent()).removeView(mContextView);
        }
    }

    public void registEvent() {

        EventBus.getDefault().register(this);
    }

    public void unregistEvent() {
        EventBus.getDefault().unregister(this);
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
