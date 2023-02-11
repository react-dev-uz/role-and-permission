package uz.pdp.roleandpermission.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.roleandpermission.payload.RoleDTO;
import uz.pdp.roleandpermission.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasAuthority('ADD_USER')")
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleDTO dto) {
        return roleService.addRole(dto);
    }


    @GetMapping("/permissions")
    public ResponseEntity<?> getPermissions() {
        return roleService.getPermissions();
    }
}
