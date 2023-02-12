package uz.pdp.roleandpermission.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //    @CheckPermission("ADD_ROLE")
    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody @Valid RoleDTO dto) {
        return roleService.addRole(dto);
    }

    //    @CheckPermission("EDIT_ROLE")
    @PreAuthorize("hasAuthority('EDIT_ROLE')")
    @PutMapping
    public ResponseEntity<?> editRole(@RequestBody @Valid RoleDTO dto) {
        return roleService.addRole(dto);
    }


    @GetMapping("/permissions")
    public ResponseEntity<?> getPermissions() {
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return roleService.getPermissions();
    }

    @Secured({"ADD_ROLE", "EDIT_ROLE", "DELETE_ROLE", "VIEW_ROLE"})
    @GetMapping
    public ResponseEntity<?> getRoles() {
        return roleService.getRoles();
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "roleId") Long roleId) {
        return roleService.deleteRole(roleId);
    }
}
