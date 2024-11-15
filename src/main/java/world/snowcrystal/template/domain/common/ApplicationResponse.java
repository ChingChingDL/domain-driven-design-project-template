package world.snowcrystal.template.domain.common;

import lombok.Data;
import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;

import java.io.Serializable;

/**
 * 通用返回类
 *
 * @param <T>
 */
@Data
public class ApplicationResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public ApplicationResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
    public ApplicationResponse(ApplicationResponseStatusCode code, T data, String message) {
        this.code = code.getCode();
        this.data = data;
        this.message = message;
    }


    public ApplicationResponse(int code, T data) {
        this(code, data, "");
    }

    public ApplicationResponse(ApplicationResponseStatusCode applicationResponseStatusCode) {
        this(applicationResponseStatusCode.getCode(), null, applicationResponseStatusCode.getMessage());
    }

    public ApplicationResponse(ApplicationResponseStatusCode status, T data) {
        this(status.getCode(), data, status.getMessage());
    }


    public static <T> ApplicationResponse<T> success(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.SUCCESS, data);
    }
    public static <T> ApplicationResponse<T> success() {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.SUCCESS, null);
    }

    //    PARAMS_ERROR(40000, "请求参数错误"),
    public static <T> ApplicationResponse<T> paramsError(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.PARAMS_ERROR, data);
    }

    //    NOT_LOGIN_ERROR(40100, "未登录"),
    public static <T> ApplicationResponse<T> notLogin(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.NOT_LOGIN_ERROR, data);
    }

    //    NO_AUTH_ERROR(40101, "无权限"),
    public static <T> ApplicationResponse<T> noAuthentication(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.NO_AUTH_ERROR, data);
    }

    //    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    public static <T> ApplicationResponse<T> notFound(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.NOT_FOUND_ERROR, data);
    }

    //    FORBIDDEN_ERROR(40300, "禁止访问"),
    public static <T> ApplicationResponse<T> forbidden(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.FORBIDDEN_ERROR, data);
    }

    //    SYSTEM_ERROR(50000, "系统内部异常"),
    public static <T> ApplicationResponse<T> systemError(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.SYSTEM_ERROR, data);
    }

    //    OPERATION_ERROR(50001, "操作失败");
    public static <T> ApplicationResponse<T> operationError(T data) {
        return new ApplicationResponse<>(ApplicationResponseStatusCode.OPERATION_ERROR, data);
    }
    public static <T> ApplicationResponse<T> noOperation(String message){
        return new ApplicationResponse<>(ApplicationResponseStatusCode.NO_OPERATION, null,message);
    }
    public static <T> ApplicationResponse<T> noOperation(T data){
        return new ApplicationResponse<>(ApplicationResponseStatusCode.NO_OPERATION, data);
    }


}
