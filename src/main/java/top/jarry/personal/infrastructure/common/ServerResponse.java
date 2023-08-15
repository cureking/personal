package top.jarry.personal.infrastructure.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author king
 */
public class ServerResponse<T> implements Serializable {

    private final boolean success;

    @Getter
    private T data;
    @Getter
    private String msg;

    private ServerResponse(boolean success) {
        this.success = success;
    }

    private ServerResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private ServerResponse(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(true);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(true, msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(true, data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(true, msg, data);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(false);
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(false, errorMessage);
    }

    public boolean isSuccess() {
        return this.success;
    }

    public boolean getSuccess() {
        return success;
    }

}