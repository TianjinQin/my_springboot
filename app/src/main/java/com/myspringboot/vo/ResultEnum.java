package com.myspringboot.vo;
public enum ResultEnum {
    
    OK("成功"),
    FAIL("失败"),
    TIMEOUT("超时，但是非错误"),
    RETRY("需要重试的错误"),
    PENDING("进行中"),
    UPDATE("更新");
    
    public String result;
    
    private ResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}
