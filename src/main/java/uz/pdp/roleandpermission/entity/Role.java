package uz.pdp.roleandpermission.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.roleandpermission.entity.enums.Permission;
import uz.pdp.roleandpermission.entity.template.AbstractEntity;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

//    @Enumerated(EnumType.STRING)
//    private RoleName roleName;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<Permission> permissions;

    @Column(name = "description", length = 500)
    private String description;

    public Role(String name, Set<Permission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }
}
