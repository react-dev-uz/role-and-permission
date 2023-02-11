package uz.pdp.roleandpermission.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Password cannot be null")
    private String password;
}
