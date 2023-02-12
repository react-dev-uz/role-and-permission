package uz.pdp.roleandpermission.aop;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CheckPermission {
    String value();
}
