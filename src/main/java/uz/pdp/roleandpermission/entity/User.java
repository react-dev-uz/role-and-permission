package uz.pdp.roleandpermission.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.roleandpermission.entity.enums.Permission;
import uz.pdp.roleandpermission.entity.template.AbstractEntity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User extends AbstractEntity implements UserDetails {

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean enabled = false;

    private boolean accountNonExpired = true;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;

    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Permission permission : this.role.getPermissions()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
//        for (Permission permission : this.role.getPermissions()) {
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return permission.name();
//                }
//            });
//        }
        return grantedAuthorities;
    }
}
