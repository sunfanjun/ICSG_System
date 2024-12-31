package com.zjut.springbootprj.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 接口统一返回包装类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private String code;//业务状态码
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(Constants.CODE_200, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(Constants.CODE_200, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(Constants.CODE_400, message, null);
    }
    public static Result error(String code,String message) {
        return new Result(code, message, null);
    }
}
