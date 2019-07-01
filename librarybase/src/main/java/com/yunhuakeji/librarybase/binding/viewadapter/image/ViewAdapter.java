package com.yunhuakeji.librarybase.binding.viewadapter.image;


import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yunhuakeji.librarybase.glide.GlideLoadUtils;

/**
 * Created by zsp on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes","radius"}, requireAll = false)
    public static void setImageUri(ImageView imageView, Object url, int placeholderRes, int radius) {
        if (url != null) {
            //使用Glide框架加载图片
            GlideLoadUtils.getInstance().glideLoad(imageView.getContext(), url, imageView, placeholderRes, radius);
        }
    }
}

