package uz.pdp.roleandpermission.exception;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
public class ResourcesNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String resourceField;
    private final Object object;
}
