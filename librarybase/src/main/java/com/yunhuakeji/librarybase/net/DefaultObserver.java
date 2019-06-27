package com.yunhuakeji.librarybase.net;

import com.google.gson.JsonParseException;
import com.yunhuakeji.librarybase.net.entity.BaseEntity;
import com.yunhuakeji.librarybase.util.ZLog;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

import static com.yunhuakeji.librarybase.net.DefaultObserver.ExceptionReason.BAD_NETWORK;
import static com.yunhuakeji.librarybase.net.DefaultObserver.ExceptionReason.CONNECT_ERROR;
import static com.yunhuakeji.librarybase.net.DefaultObserver.ExceptionReason.CONNECT_TIMEOUT;
import static com.yunhuakeji.librarybase.net.DefaultObserver.ExceptionReason.PARSE_ERROR;
import static com.yunhuakeji.librarybase.net.DefaultObserver.ExceptionReason.UNKNOWN_ERROR;
import static com.yunhuakeji.librarybase.net.RequestCodeUtil.SUCCESS;
import static com.yunhuakeji.librarybase.net.RequestCodeUtil.USER_AUTHENTICATION_FAILURE_CODE;
import static com.yunhuakeji.librarybase.net.RequestCodeUtil.USER_CHANGE_EQUIPMENT_FAILURE_CODE;


/**
 * 请求集中处理类
 *
 * @author Andy
 * created at 2019/3/14 0014 18:03
 */

public abstract class DefaultObserver<T> implements Observer<T> {

    public DefaultObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        dismissProgress();
        boolean isSuccess = false;
        if (response instanceof BaseEntity) {
            isSuccess = ((BaseEntity) response).getCode().equals(SUCCESS);
        }
        if (isSuccess) {
            onSuccess(response);
        } else {
            onFail(response);
        }
    }

    private void dismissProgress() {

    }

    @Override
    public void onError(Throwable e) {
        ZLog.e(e.toString());
        dismissProgress();
        if (e instanceof HttpException) {     //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(PARSE_ERROR);
        } else {
            onException(UNKNOWN_ERROR);
        }
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 服务器返回数据，但响应码不为200
     *
     * @param response 服务器返回的数据
     */
    public void onFail(T response) {
        String message = null;
        String code;
        if (response instanceof BaseEntity) {
            message = ((BaseEntity) response).getMessage();
            code = ((BaseEntity) response).getCode();
            if (code.equals(USER_AUTHENTICATION_FAILURE_CODE)) {
            } else if (code.equals(USER_CHANGE_EQUIPMENT_FAILURE_CODE)) {
            }
        }

    }

    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        switch (reason) {
            case CONNECT_ERROR:
                break;

            case CONNECT_TIMEOUT:
                break;

            case BAD_NETWORK:
                break;

            case PARSE_ERROR:
                break;

            case UNKNOWN_ERROR:
            default:
                break;
        }
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }

    /**
     * 缺省页
     *
     * @author Andy
     * created at 2019/3/26 0026 11:50
     */
    private void errorPage(int image, String hint) {
    }
}
