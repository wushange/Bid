package com.dmcc.bid.ui.home;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.ui.bidlist.BidListActivity;
import com.dmcc.bid.ui.login.LoginActivity;
import com.dmcc.bid.util.ACache;
import com.dmcc.bid.util.StringUtil;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wushange on 2016/10/24.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.img_home_search)
    ImageView mSearch;
    @BindView(R.id.et_home_input)
    EditText mEdittext;
    @BindView(R.id.tv_logout)
    TextView tvLogout;
    Animation backAnimation1, backAnimation2;
    @BindView(R.id.iv_back1)
    RelativeLayout ivBack1;
    @BindView(R.id.iv_back2)
    RelativeLayout ivBack2;

    @Override
    public void initInjector() {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {    // 允许使用transitions
        mEdittext.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                goSearch();
            }
            return false;
        });
        mSearch.setOnClickListener(v -> goSearch());
        tvLogout.setOnClickListener(v -> {
            ACache.get(this).clear();
            mBaseOperation.forwardAndFinish(LoginActivity.class);
        });
        backAnimation1 = AnimationUtils.loadAnimation(getContext(), R.anim.a);
        backAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.b);
        ivBack1.setAnimation(backAnimation1);
        ivBack2.setAnimation(backAnimation2);
    }

    private void goSearch() {
        if (ACache.get(this).getAsObject("user") == null) {
            mBaseOperation.getmSweetAlertDialog(SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("需要登录")
                    .setContentText("您还没有登录")
                    .setConfirmText("去登陆")
                    .setConfirmClickListener(sweetAlertDialog -> {
                        mBaseOperation.dissLoading();
                        mBaseOperation.forward(LoginActivity.class);
                    }).show();
            return;
        }
        if (StringUtil.isBlank(getInputText())) {
            mBaseOperation.showSweetError("错误", "关键词不能为空！");
            return;
        }
        mBaseOperation.addParameter("keyWords", getInputText());
//        mBaseOperation.forward(BidInfoListActivity.class, ActivityOptions.makeSceneTransitionAnimation(getContext(), mEdittext, "transitionImg").toBundle());
        mBaseOperation.forward(BidListActivity.class);
    }

    public String getInputText() {
        return mEdittext.getText().toString();
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
