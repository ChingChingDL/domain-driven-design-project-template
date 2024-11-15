package world.snowcrystal.template.domain.user.exception;

import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;

public class AccountTakenException extends BusinessException {
    public AccountTakenException(String message) {
        super(ApplicationResponseStatusCode.NO_OPERATION, message);
    }

    public AccountTakenException() {
        super(ApplicationResponseStatusCode.NO_OPERATION);
    }

}
