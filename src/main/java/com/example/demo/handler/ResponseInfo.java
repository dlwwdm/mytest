package com.example.demo.handler;

public class ResponseInfo<T> {
    protected String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    protected String msg;
    private T data;

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public ResponseInfo(){
        this.code = "0";
        this.msg = "操作成功";
    }


    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public  ResponseInfo(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
     * @param data
     */
    public ResponseInfo(T data){
        this.code = "0";
        this.msg = "操作成功";
        this.data = data;
    }
    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     * @param data
     * @param msg
     */
    public ResponseInfo(T data, String msg) {
        this.data = data;
        this.code = "0";
        this.msg = msg;
    }

}
