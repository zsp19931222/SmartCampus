package com.yunhuakeji.librarybase.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * 吐司
 * author：Andy on 2019/4/29 0029-10:17
 * email:zsp872126510@gmail.com
 */

public class ToastUtil {

    /**
     * 错误提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void errorToast(Context context, String msg, boolean withIcon) {
        Toasty.error(context, msg, Toast.LENGTH_SHORT, withIcon).show();
    }

    /**
     * 成功提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void successToast(Context context, String msg, boolean withIcon) {
        Toasty.success(context, msg, Toast.LENGTH_SHORT, withIcon).show();
    }

    /**
     * info提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void infoToast(Context context, String msg, boolean withIcon) {
        Toasty.info(context, msg, Toast.LENGTH_SHORT, withIcon).show();
    }

    /**
     * warning提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param withIcon 是否显示icon
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void warningToast(Context context, String msg, boolean withIcon) {
        Toasty.warning(context, msg, Toast.LENGTH_SHORT, withIcon).show();
    }

    /**
     * normal提示
     *
     * @param context 上下文
     * @param msg     提示信息
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void normalToast(Context context, String msg) {
        Toasty.normal(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * normal提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param drawable 图片
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void normalToast(Context context, String msg, Drawable drawable) {
        Toasty.normal(context, msg, Toast.LENGTH_SHORT, drawable).show();
    }
    /**
     * custom提示
     *
     * @param context  上下文
     * @param msg      提示信息
     * @param drawable 图片
     * @author Andy
     * created at 2019/4/29 0029 10:23
     */
    public static void customToast(Context context, int msg, Drawable drawable,int tintColor,int duration,boolean withIcon,boolean shouldTint) {
        Toasty.custom(context, msg, drawable, tintColor, duration, withIcon,
                shouldTint).show();
    }

}
