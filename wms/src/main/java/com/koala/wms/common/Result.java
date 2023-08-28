package com.koala.wms.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Long total;
    private Object data;

    public static Result fail() {
        return result(400, "失敗", 0L, null);
    }

    public static Result sucess() {
        return result(200, "成功", 0L, null);
    }

    public static Result sucess(Object data) {
        return result(200, "成功", 0L, data);
    }

    public static Result sucess(Object data, Long total) {
        return result(200, "成功", total, data);
    }

    public static Result result(int code, String msg, Long total, Object data) {
        Result result = new Result();
        result.setData(data);
        result.setMsg(msg);
        result.setCode(code);
        result.setTotal(total);
        return result;
    }
}
