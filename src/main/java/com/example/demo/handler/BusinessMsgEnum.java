package com.example.demo.handler;

public enum  BusinessMsgEnum {
    /**
     * 参数异常
     */
    PARAMETER_VALIDATION_EXCEPTION("100011", "参数不合法!"),
    USER_HAS_REGISTER("10002", "用户已存在!"),
    USER_NOT_EXIST("10003", "用户不存在!"),
    USER_PASSWORD_NOT_EQUALS("10004", "密码错误!"),
    /**
     * 等待超时
     */
    SERVICE_TIME_OUT("102", "服务超时！"),
    /**
     * 参数过大
     */
    PARMETER_BIG_EXCEPTION("903", "内容不能超过200字，请重试!"),
    /**
     * 数据库操作失败
     */
    DATABASE_EXCEPTION("509", "数据库操作异常，请联系管理员！"),
    /**
     * 500 : 一劳永逸的提示也可以在这定义
     */
    UNEXPECTED_EXCEPTION("500", "系统发生异常，请联系管理员！");
    // 还可以定义更多的业务异常



    /**
     * 消息码
     */
    private String code;
    /**
     * 消息内容
     */
    private String msg;

    private BusinessMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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

}
