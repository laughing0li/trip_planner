package com.banjo.planner.utils;
import lombok.Data;
import java.io.Serializable;

/**
 * Tools
 * Used for unified management of
 * the results returned by RESTful APIs
 */

@Data
public class Results implements Serializable {
    public static int SUCCESS_CODE = 0;
    public static int FAIL_CODE = 1;

    private int code;
    private String msg;
    private Object data;


    private Results (int code, String msg, Object data) {
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
    }

    public static Results success() {
        return new Results(SUCCESS_CODE,null,null);
    }
    public static Results success(Object data) {
        return new Results(SUCCESS_CODE,"",data);
    }
    public static Results fail(String message) {
        return new Results(FAIL_CODE,message,null);
    }

}