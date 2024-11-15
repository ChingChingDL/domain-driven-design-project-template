package world.snowcrystal.template.domain.common.exception;

import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;

/**
 * 自定义异常类
 *
 *
 *
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ApplicationResponseStatusCode applicationResponseStatusCode) {
        super(applicationResponseStatusCode.getMessage());
        this.code = applicationResponseStatusCode.getCode();
    }

    public BusinessException(ApplicationResponseStatusCode applicationResponseStatusCode, String message) {
        super(message);
        this.code = applicationResponseStatusCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
