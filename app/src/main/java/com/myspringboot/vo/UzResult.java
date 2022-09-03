/**
 *
 */
package com.myspringboot.vo;

/**
 * @author chentao
 * @date: 2018年9月11日 下午3:35:02
 */
public class UzResult<T> extends BaseResult {

    public UzResult() {
        this(null);
    }

    public UzResult(T data) {
        super(BaseResult.SUCCESS.getMessage(), BaseResult.SUCCESS.getCode());
        this.data = data;
    }

    /**
     * 返回的数据
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
