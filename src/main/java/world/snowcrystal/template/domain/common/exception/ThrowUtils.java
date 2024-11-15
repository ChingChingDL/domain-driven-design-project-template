package world.snowcrystal.template.domain.common.exception;

import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;

/**
 * 抛异常工具类
 *
 *
 *
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param applicationResponseStatusCode
     */
    public static void throwIf(boolean condition, ApplicationResponseStatusCode applicationResponseStatusCode) {
        throwIf(condition, new BusinessException(applicationResponseStatusCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param applicationResponseStatusCode
     * @param message
     */
    public static void throwIf(boolean condition, ApplicationResponseStatusCode applicationResponseStatusCode, String message) {
        throwIf(condition, new BusinessException(applicationResponseStatusCode, message));
    }
}
