package org.example.entity;

public class CommonResult<T> {
    private Integer code;
    private String message;
    private T result;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
