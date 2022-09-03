 /**
 * 
 */
package com.myspringboot.vo;

/**
 * @author chentao
 *
 * @date:   2018年9月11日 下午3:38:29   
 */
public class BaseResult {

    /**
     * 返回信息
     * */
    protected String  message;
    
    /**
     * 结果提示，ok：代表成功
     *
     * */
    protected String code;
    
    public static final BaseResult SUCCESS = new BaseResult("", ResultEnum.OK.toString());
    public static final BaseResult FAIL = new BaseResult("", ResultEnum.FAIL.toString());
    public static final BaseResult TIMEOUT = new BaseResult("", ResultEnum.TIMEOUT.toString());
    public static final BaseResult SYSTEMERROR = new BaseResult("系统错误", ResultEnum.FAIL.toString());
    public static final BaseResult RETRYERROR = new BaseResult("请重试", ResultEnum.RETRY.toString());
    
    public BaseResult() { }
    
    public BaseResult(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}