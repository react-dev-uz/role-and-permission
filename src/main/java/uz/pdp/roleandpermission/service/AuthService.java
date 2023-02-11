package uz.pdp.roleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.roleandpermission.entity.User;
import uz.pdp.roleandpermission.payload.RegisterDTO;
import uz.pdp.roleandpermission.repository.UserRepository;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;


@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> registerUser(RegisterDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            return status(UNPROCESSABLE_ENTITY).body("Username is already taken");
        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                dto.getPassword(),
                null,
                true
        );
        return null;
    }
}
