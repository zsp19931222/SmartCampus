package com.yunhuakeji.modellogin.activity;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.yunhuakeji.librarybase.base.BaseViewModel;
import com.yunhuakeji.librarybase.binding.command.BindingAction;
import com.yunhuakeji.librarybase.binding.command.BindingCommand;
import com.yunhuakeji.librarybase.net.DefaultObserver;
import com.yunhuakeji.librarybase.net.IdeaApi;
import com.yunhuakeji.librarybase.net.entity.LoginEntity;
import com.yunhuakeji.librarybase.rsa.RSAKYEUtil;
import com.yunhuakeji.librarybase.util.ParameterUtil;
import com.yunhuakeji.librarybase.util.ZLog;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * description:
 * author:created by Andy on 2019/6/27 0027 14:42
 * email:zsp872126510@gmail.com
 */
public class LoginViewModel extends BaseViewModel {

    public ObservableField<String> text1 = new ObservableField<>("账号密码登录");
    public ObservableField<String> text2 = new ObservableField<>("请使用数字化校园账号密码登录！");
    public ObservableField<String> text3 = new ObservableField<>("账号");
    public ObservableField<String> text4 = new ObservableField<>("请输入学号/教职工号");
    public ObservableField<String> text5 = new ObservableField<>("密码");
    public ObservableField<String> text6 = new ObservableField<>("请输入密码");
    public ObservableField<String> text7 = new ObservableField<>("忘记密码？");
    public ObservableField<String> text8 = new ObservableField<>("zsp");
    public ObservableField<String> button = new ObservableField<>("获取数据");

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand onClickBindingCommand = new BindingCommand(() -> getData());

    private void getData() {
        try {
            Map<String, String> parameter = ParameterUtil.getInstance().getParameter();
            parameter.put("password", RSAKYEUtil.encryptRSA.encryptByKeyString(RSAKYEUtil.PUBLIC_KEY, "033520"));
            IdeaApi.getApiService()
                    .postAuthentication("2018010062", parameter)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<LoginEntity>() {
                        @Override
                        public void onSuccess(LoginEntity response) {
                            ZLog.e(response);
                            text8.set(response.getContent().toString());
                        }
                    });
        } catch (Exception e) {
            ZLog.e(e.toString());

        }

    }
}
