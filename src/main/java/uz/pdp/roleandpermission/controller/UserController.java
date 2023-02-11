package uz.pdp.roleandpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> createUser(UserDTO dto) {
        return null;
    }
}
