package com.yunhuakeji.modellogin.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yunhuakeji.librarybase.base.BaseActivity;
import com.yunhuakeji.librarybase.util.constant.ARouterPathConstants;
import com.yunhuakeji.modellogin.R;
import com.yunhuakeji.modellogin.BR;
import com.yunhuakeji.modellogin.databinding.ActivityLoginMainBinding;

/**
 * description:
 * author:created by Andy on 2019/6/26 0026 14:59
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPathConstants.LOGIN.LOGIN_MAIN)
public class LoginMainActivity extends BaseActivity<ActivityLoginMainBinding, LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }
}
