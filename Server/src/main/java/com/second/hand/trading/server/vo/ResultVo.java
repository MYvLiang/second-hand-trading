package com.second.hand.trading.server.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.second.hand.trading.server.enums.ErrorMsg;

/**
 * @author myl
 * @param <T> 代返回数据 的 数据类型
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultVo<T> {

    private Integer status_code;
    private String msg;
    private T data;

    //直接返回成功状态码
    public static ResultVo success(){
        ResultVo resultVo=new ResultVo();
        resultVo.setStatus_code(1);
        return resultVo;
    }

    //返回成功状态码的同时返回对象
    public static <T>ResultVo success(T data){
        ResultVo<T> resultVo=new ResultVo<>();
        resultVo.setStatus_code(1);
        resultVo.setData(data);
        return resultVo;
    }

    //直接返回错误状态码和错误信息
    public static ResultVo fail(ErrorMsg errorMsg){
        ResultVo resultVo=new ResultVo();
        resultVo.setStatus_code(0);
        resultVo.setMsg(errorMsg.getMsg());
        return resultVo;
    }

    //返回错误状态码和错误信息的同时返回错误对象
    public static <T>ResultVo fail(ErrorMsg errorMsg,T data){
        ResultVo<T> resultVo=new ResultVo<>();
        resultVo.setStatus_code(0);
        resultVo.setMsg(errorMsg.getMsg());
        resultVo.setData(data);
        return resultVo;
    }


    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultVo(Integer status_code, String msg, T data) {
        this.status_code = status_code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVo() {
    }
}
