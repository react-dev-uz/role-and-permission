package uz.pdp.roleandpermission.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@EqualsAndHashCode(callSuper = true)
@ResponseStatus(HttpStatus.FORBIDDEN)
@AllArgsConstructor
public class ForbiddenException extends RuntimeException {
    private String type;
    private String message;
}
