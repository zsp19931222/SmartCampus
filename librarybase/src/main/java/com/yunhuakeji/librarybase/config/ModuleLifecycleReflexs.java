package com.yunhuakeji.librarybase.config;

/**
 * Created by zsp on 2018/6/21 0021.
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */

public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.yunhuakeji.librarybase.base.BaseModuleInit";
    //登录验证模块
    private static final String SignInit = "com.yunhuakeji.modellogin.LoginModuleInit";
    private static final String LaunchInit = "com.yunhuakeji.model_launch.LaunchModuleInit";

    public static String[] initModuleNames = {BaseInit,SignInit,LaunchInit};
}
