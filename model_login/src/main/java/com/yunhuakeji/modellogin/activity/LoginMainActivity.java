package com.yunhuakeji.modellogin.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.yunhuakeji.librarybase.base.BaseActivity;
import com.yunhuakeji.librarybase.bus.RxBus;
import com.yunhuakeji.librarybase.bus.RxSubscriptions;
import com.yunhuakeji.librarybase.util.PermissionsUtils;
import com.yunhuakeji.librarybase.util.PictureUtils;
import com.yunhuakeji.librarybase.util.constant.ARouterPathConstants;
import com.yunhuakeji.modellogin.BR;
import com.yunhuakeji.modellogin.R;
import com.yunhuakeji.modellogin.databinding.ActivityLoginMainBinding;

import io.reactivex.disposables.Disposable;

import static com.yunhuakeji.librarybase.util.constant.RxBusMessageEventConstants.OPEN_CAMERA;
import static com.yunhuakeji.librarybase.util.constant.RxBusMessageEventConstants.OPEN_GALLERY;

/**
 * description:
 * author:created by Andy on 2019/6/26 0026 14:59
 * email:zsp872126510@gmail.com
 */
@Route(path = ARouterPathConstants.LOGIN.LOGIN_MAIN)
public class LoginMainActivity extends BaseActivity<ActivityLoginMainBinding, LoginViewModel> {
    private Disposable rxBusSubscriber;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    public void openPictureSelector(View view) {
        PermissionsUtils.getInstance().getPermissionsWithFragmentActivity(this, OPEN_CAMERA, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void openPictureSelector1(View view) {
        PermissionsUtils.getInstance().getPermissionsWithFragmentActivity(this, OPEN_GALLERY, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxBusSubscriber = RxBus.getDefault().toObservable(String.class).subscribe(s -> {
            switch (s) {
                case OPEN_GALLERY:
                    PictureUtils.getInstance().openGalleryWithActivity(this);
                    break;
                case OPEN_CAMERA:
                    PictureUtils.getInstance().openCameraWithActivity(this);
                    break;
            }
        });
        RxSubscriptions.add(rxBusSubscriber);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.REQUEST_CAMERA:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        PictureSelector.obtainMultipleResult(data).get(i).getCompressPath();
                        viewModel.url.set(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath());
                        viewModel.placeholderRes.set(R.drawable.ic_delete_photo);
                        viewModel.radius.set(45);
                    }
                    break;
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        PictureSelector.obtainMultipleResult(data).get(i).getCompressPath();
                        viewModel.url.set(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath());
                        viewModel.placeholderRes.set(R.drawable.ic_delete_photo);
                        viewModel.radius.set(45);
                    }
                    break;
            }
        }
    }
}
