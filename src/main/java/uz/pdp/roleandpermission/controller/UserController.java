package uz.pdp.roleandpermission.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.roleandpermission.payload.UserDTO;
import uz.pdp.roleandpermission.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('ADD_USER')")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDTO dto) {
        return service.addUser(dto);
    }
}
