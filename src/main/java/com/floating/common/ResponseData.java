package com.floating.common;

/**
 * ResponseData 返回数据
 *
 * @author feiyu
 * @version V1.0
 * @date 2019/9/24 13:06
 **/
public class ResponseData<T> {


    /**
     * 查询成功
     */
    public static final int SUCCESS_CODE_SELECT = 0;
    /**
     * 更新成功
     */
    public static final int SUCCESS_CODE_UPDATE = 1;
    /**
     * 失败状态码
     */
    public static final int ERROR_CODE = -1;

    /**
     * 系统异常状态码
     */
    public static final int SYS_ERROR_CODE = 1000;
    /**
     * 系统异常消息
     */
    public static final String SYS_ERROR_MSG = "系统繁忙，请稍后再试！";

    /**
     * 参数设置异常
     */
    public static final String PARAMETER_ERROR_MSG = "参数错误";
    /**
     * 平台用户无权限
     */
    public static final String FROM_USER_NO_ACCESS = "对不起，您没有相应的权限";
    /**
     * 成功信息
     */
    public static final String SUCCESS_MSG = "操作成功";
    /**
     * 错误信息
     */
    public static final String ERROR_MSG = "操作失败";
    /**
     * 状态码
     */
    private int code;
    /**
     * 状态消息
     */
    private String responseMsg;
    /**
     * 相应信息
     */
    private T data;

    public ResponseData(int code, String responseMsg, T data) {
        this.code = code;
        this.responseMsg = responseMsg;
        this.data = data;
    }

    public ResponseData(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseData(int code, String responseMsg) {
        this.code = code;
        this.responseMsg = responseMsg;
    }

    public ResponseData(String errorMsg) {
        this.code = ERROR_CODE;
        this.responseMsg = errorMsg;
    }

    public ResponseData() {

    }

    public static ResponseData success(int code) {
        return new ResponseData(code, SUCCESS_MSG);
    }

    public static ResponseData success(int code, String message) {
        return new ResponseData(code, message);
    }

    public static ResponseData failed() {
        return new ResponseData(ERROR_CODE, ERROR_MSG);
    }

    public static ResponseData failed(int errorCode, String errorMsg) {
        return new ResponseData(errorCode, errorMsg);
    }

    public static ResponseData failed(String message) {
        return new ResponseData(ERROR_CODE, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
