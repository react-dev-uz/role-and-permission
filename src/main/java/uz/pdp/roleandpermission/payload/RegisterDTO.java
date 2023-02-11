package uz.pdp.roleandpermission.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotBlank
    private String fullName;

    @NotNull
    @Size(min = 4)
    private String username;

    @NotNull
    @Size(min = 7)
    private String password;

    @NotNull
    private String prePassword;
}
