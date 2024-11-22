package world.snowcrystal.template.domain.like.exception;

import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;

public class AlreadyThumpUpException extends BusinessException {
    public AlreadyThumpUpException(ApplicationResponseStatusCode applicationResponseStatusCode) {
        super(applicationResponseStatusCode);
    }

    public AlreadyThumpUpException(ApplicationResponseStatusCode applicationResponseStatusCode, String message) {
        super(applicationResponseStatusCode, message);
    }
}
