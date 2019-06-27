package com.yunhuakeji.librarybase.util;


import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/5 0005.
 * 参数处理
 */

public class ParameterUtil {

    private  final String publicNetwork = "PUBLIC_AUTHENTICATION";
    private  final String privateNetwork = "SCHOOL_AUTHENTICATION";
    private  Map<String, String> parameterMap = new HashMap<>();


    private static class ParameterUtilHolder {
        private final static ParameterUtil Instance = new ParameterUtil();
    }

    public static ParameterUtil getInstance() {
        return ParameterUtilHolder.Instance;
    }

    public ParameterUtil() {
    }

    public Map<String, String> getParameter() {
        if (parameterMap == null) {
            parameterMap = new HashMap<>();
        } else {
            parameterMap.clear();
        }
        parameterMap.put("clientCategory", "ANDROID");
        parameterMap.put("appVersion", AppUtils.getAppVersionName());
        parameterMap.put("authenticationType", privateNetwork);
        parameterMap.put("equipmentId", DeviceUtils.getAndroidID());
        parameterMap.put("equipmentName", DeviceUtils.getModel());
        parameterMap.put("universityId", "100003");
        return parameterMap;
    }
}
