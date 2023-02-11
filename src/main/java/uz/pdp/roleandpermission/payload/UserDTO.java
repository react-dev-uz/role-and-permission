package uz.pdp.roleandpermission.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(message = "Full name cannot be null")
    private String fullName;

    @NotNull(message = "Email is required")
    @Size(min = 4, message = "Password must be at least 4 characters long")
    private String username;

    @NotNull(message = "Password is required")
    @Size(min = 7, message = "Password must be at least 7 characters")
    private String password;

    @NotNull(message = "Role id is required")
    private Long roleId;
}
