package uz.pdp.roleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.roleandpermission.component.JWTProvider;
import uz.pdp.roleandpermission.entity.User;
import uz.pdp.roleandpermission.exception.ResourcesNotFoundException;
import uz.pdp.roleandpermission.payload.LoginDTO;
import uz.pdp.roleandpermission.payload.RegisterDTO;
import uz.pdp.roleandpermission.repository.RoleRepository;
import uz.pdp.roleandpermission.repository.UserRepository;
import uz.pdp.roleandpermission.utils.AppConstants;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTProvider provider;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager, JWTProvider provider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.provider = provider;
    }

    public ResponseEntity<?> registerUser(RegisterDTO dto) {
        if (!dto.getPassword().equals(dto.getPrePassword()))
            return status(BAD_REQUEST).body("Passwords are incompatible");
        if (userRepository.existsByUsername(dto.getUsername()))
            return status(UNPROCESSABLE_ENTITY).body("Username is already taken");
        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourcesNotFoundException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return status(CREATED).body("User registered successfully");
    }

    public ResponseEntity<?> loginUser(LoginDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        return ok(provider.generateToken(authenticate));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
