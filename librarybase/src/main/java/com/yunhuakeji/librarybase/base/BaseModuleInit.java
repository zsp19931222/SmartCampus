package com.yunhuakeji.librarybase.base;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yunhuakeji.librarybase.BuildConfig;
import com.yunhuakeji.librarybase.util.Utils;
import com.yunhuakeji.librarybase.util.ZLog;

/**
 * Created by zsp on 2018/6/21 0021.
 * 基础库自身初始化操作
 */

public class BaseModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        //dex分包
        MultiDex.install(application);
        //常用工具类
        Utils.init(application);
        com.blankj.utilcode.util.Utils.init(application);
        //开启打印日志
        ZLog.init(true);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        ZLog.e("基础层初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        ZLog.e("基础层初始化 -- onInitLow");
        return false;
    }
}
