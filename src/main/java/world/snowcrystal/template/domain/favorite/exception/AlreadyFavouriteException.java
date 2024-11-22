package world.snowcrystal.template.domain.favorite.exception;

import world.snowcrystal.template.domain.common.enums.ApplicationResponseStatusCode;
import world.snowcrystal.template.domain.common.exception.BusinessException;

public class AlreadyFavouriteException extends BusinessException {
    public AlreadyFavouriteException(ApplicationResponseStatusCode applicationResponseStatusCode) {
        super(applicationResponseStatusCode);
    }

    public AlreadyFavouriteException(ApplicationResponseStatusCode applicationResponseStatusCode, String message) {
        super(applicationResponseStatusCode, message);
    }
}
