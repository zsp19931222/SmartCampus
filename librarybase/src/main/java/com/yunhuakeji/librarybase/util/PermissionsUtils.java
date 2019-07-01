package com.yunhuakeji.librarybase.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yunhuakeji.librarybase.bus.RxBus;

import io.reactivex.observers.DefaultObserver;

import static com.yunhuakeji.librarybase.util.constant.TipsConstants.GET_PERMISSIONS_FAILED;

/**
 * description:获取相关权限
 * author:created by Andy on 2019/6/28 0028 14:55
 * email:zsp872126510@gmail.com
 */
public class PermissionsUtils {
    private boolean success = false;

    private static class PermissionsUtilsHoler {
        private final static PermissionsUtils INSTANCE = new PermissionsUtils();
    }

    public static PermissionsUtils getInstance() {
        return PermissionsUtilsHoler.INSTANCE;
    }

    public PermissionsUtils() {
    }

    /**
     * @param context
     * @param permission 需要申请的权限
     * @param event      事件
     * @Description: 获取权限
     * @Author:Andy
     * @Date:2019/6/28 0028 14:58
     */
    public boolean getPermissionsWithFragmentActivity(FragmentActivity context, Object event, String... permission) {
        new RxPermissions(context)
                .request(permission)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            success = true;
                            RxBus.getDefault().post(event);
                        } else {
                            ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.errorToast(context, GET_PERMISSIONS_FAILED, false);
                        success = false;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return success;
    }

    /**
     * @param fragment
     * @param permission 需要申请的权限
     * @param event      事件
     * @Description: 获取权限
     * @Author:Andy
     * @Date:2019/6/28 0028 14:58
     */
    public boolean getPermissionsWithFragment(Fragment fragment, Object event, String... permission) {
        new RxPermissions(fragment)
                .request(permission)
                .subscribe(new DefaultObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (aBoolean) {
                            success = true;
                            RxBus.getDefault().post(event);
                        } else {
                            ToastUtil.errorToast(fragment.getContext(), GET_PERMISSIONS_FAILED, false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtil.errorToast(fragment.getContext(), GET_PERMISSIONS_FAILED, false);
                        success = false;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return success;
    }
}
