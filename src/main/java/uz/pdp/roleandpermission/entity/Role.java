package uz.pdp.roleandpermission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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

    @Column(nullable = false)
    private String name;

//    @Enumerated(EnumType.STRING)
//    private RoleName roleName;

    @ElementCollection
    private Set<Permission> permissions;
}
