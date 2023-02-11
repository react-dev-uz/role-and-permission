package uz.pdp.roleandpermission.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.pdp.roleandpermission.entity.Role;
import uz.pdp.roleandpermission.entity.User;
import uz.pdp.roleandpermission.entity.enums.Permission;
import uz.pdp.roleandpermission.repository.RoleRepository;
import uz.pdp.roleandpermission.repository.UserRepository;
import uz.pdp.roleandpermission.utils.AppConstants;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static uz.pdp.roleandpermission.entity.enums.Permission.*;


@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public synchronized void run(String... args) {
        if (initialMode.equalsIgnoreCase("always")) {
            loadRole();
            loadUser();
        }
    }

    public void loadRole() {
        roleRepository.saveAll(List.of(
                new Role(AppConstants.ADMIN, Set.of(Permission.values())),
                new Role(AppConstants.USER, Set.of(ADD_COMMENT, EDIT_COMMENT, DELETE_MY_COMMENT))
        ));
    }

    public void loadUser() {
        Optional<Role> optionalAdmin = roleRepository.findByName(AppConstants.ADMIN);
        Optional<Role> optionalUser = roleRepository.findByName(AppConstants.USER);
        if (optionalAdmin.isPresent() && optionalUser.isPresent()) {
            userRepository.saveAll(List.of(
                    new User("John Doe", "admin", "root123@", optionalAdmin.get(), true),
                    new User("Abror Abrorov", "user1", "root123@", optionalUser.get(), true),
                    new User("Abdusattor Abdusattorov", "user2", "root123@", optionalUser.get(), true)
            ));
        } else {
            System.out.println("User saqlanishida hatolik yuz berdi. Admin ma'lumotlar omboriga saqlanmadi");
        }
    }
}
