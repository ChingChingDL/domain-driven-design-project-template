package world.snowcrystal.template.domain.common.exception;

import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import world.snowcrystal.template.domain.common.ApplicationResponse;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApplicationResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ApplicationResponse.systemError(e.getMessage());
    }

//    @ExceptionHandler(BusinessException.class)
//    public ApplicationResponse<?> runtimeExceptionHandler(RuntimeException e) {
//        log.error("RuntimeException", e);
//        return ApplicationResponse.systemError("系统错误");
//    }

    @ExceptionHandler(ValidationException.class)
    public ApplicationResponse<?> validationExceptionHandler(ValidationException e) {
        log.error("ValidationException", e);
        return ApplicationResponse.paramsError(e.getMessage());
    }
}
