package uz.pdp.roleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.roleandpermission.entity.Role;
import uz.pdp.roleandpermission.entity.enums.Permission;
import uz.pdp.roleandpermission.payload.RoleDTO;
import uz.pdp.roleandpermission.repository.RoleRepository;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<?> addRole(RoleDTO dto) {
        if (roleRepository.existsByName(dto.getName()))
            return status(UNPROCESSABLE_ENTITY).body("Role name already exists");
        Role role = new Role(
                dto.getName(),
                dto.getPermissions(),
                dto.getDescription()
        );
        roleRepository.save(role);
        return status(CREATED).body("Role successfully created");
    }

    public ResponseEntity<?> getPermissions() {
        return ok(Permission.values());
    }
}
