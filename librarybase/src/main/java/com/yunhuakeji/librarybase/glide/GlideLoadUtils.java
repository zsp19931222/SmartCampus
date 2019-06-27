package com.yunhuakeji.librarybase.glide;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.yunhuakeji.librarybase.util.SizeUtils;
import com.yunhuakeji.librarybase.util.ZLog;

/**
 * description:Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
 * author:created by Andy on 2019/6/20 0020 15:33
 * email:zsp872126510@gmail.com
 */
public class GlideLoadUtils implements GlideLoadInterface {
    public static final int BORDER_RADIUS = 45;
    public static final int NORMAL = 0;

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */

    //判断Activity是否Destroy
    public static boolean isDestroy(Activity activity) {
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    private static class GlideLoadUtilsHolder {
        private final static GlideLoadUtils INSTANCE = new GlideLoadUtils();
    }

    public static GlideLoadUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    public GlideLoadUtils() {
    }

    /**
     * @param context
     * @param url           加载图片的url地址  String
     * @param imageView     加载图片的ImageView 控件
     * @param default_image 图片展示错误的本地图片 id
     * @param radius        圆角（0-无圆角，45-圆图片，其他值-相对应的圆角图片）
     * @Description:Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     * @Author:Andy
     * @Date:2019/6/20 0020 15:41
     */
    @Override
    public void glideLoad(Context context, Object url, ImageView imageView, int default_image, int radius) {
        if (context != null) {
            if (!isDestroy((Activity) context)) {
                Glide.with(context)
                        .load(url)
                        .apply(new RequestOptions()
                                .error(default_image)
                                .fitCenter()
                                .centerCrop()
                                .placeholder(default_image)
                                .transform(new GlideRoundTransform(context, SizeUtils.dp2px(radius)))
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(imageView);
            }
        } else {
            ZLog.i("Picture loading failed,context is null");
        }
    }
}
