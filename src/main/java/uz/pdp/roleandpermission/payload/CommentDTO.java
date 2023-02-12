package uz.pdp.roleandpermission.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTO {

    @NotBlank
    private String text;

    @NotNull
    private Long postId;
}
