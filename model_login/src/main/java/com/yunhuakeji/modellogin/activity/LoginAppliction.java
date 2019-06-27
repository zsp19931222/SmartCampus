package com.yunhuakeji.modellogin.activity;

import android.app.Application;

import com.yunhuakeji.librarybase.base.IModuleInit;

/**
 * description:
 * author:created by Andy on 2019/6/26 0026 16:59
 * email:zsp872126510@gmail.com
 */
public class LoginAppliction implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {

        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        return false;
    }
}
