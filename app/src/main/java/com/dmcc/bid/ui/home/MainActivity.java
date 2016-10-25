package com.dmcc.bid.ui.home;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import com.dmcc.bid.R;
import com.dmcc.bid.base.BaseActivity;
import com.dmcc.bid.ui.bidlist.BidListActivity;
import com.dmcc.bid.util.StringUtil;

import butterknife.BindView;

/**
 * Created by wushange on 2016/10/24.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.img_home_search)
    ImageView mSearch;

    @BindView(R.id.et_home_input)
    EditText mEdittext;

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
    }

    private void goSearch() {
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
