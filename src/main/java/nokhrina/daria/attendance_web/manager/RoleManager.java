package nokhrina.daria.attendance_web.manager;

import jakarta.transaction.Transactional;
import nokhrina.daria.attendance_web.enums.web.Privileges;
import nokhrina.daria.attendance_web.enums.web.RoleName;
import nokhrina.daria.attendance_web.model.web.Privilege;
import nokhrina.daria.attendance_web.model.web.Role;
import nokhrina.daria.attendance_web.repo.web.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoleManager {

    private static final Map<RoleName, Set<Privileges>> DEFINITIONS = new EnumMap<>(RoleName.class);

    static {
        DEFINITIONS.put(RoleName.ADMIN, EnumSet.allOf(Privileges.class));
        DEFINITIONS.put(RoleName.TEACHER, EnumSet.of(
                Privileges.VIEW_ALL_COURSES,
                Privileges.EDIT_OWNED_COURSES));
        DEFINITIONS.put(RoleName.STUDENT, EnumSet.of(Privileges.VIEW_ALL_COURSES));
        DEFINITIONS.put(RoleName.USER, EnumSet.of(Privileges.VIEW_ALL_COURSES));
    }

    private final RoleRepository roleRepository;
    private final PrivilegeManager privilegeManager;

    private volatile Role adminRole;
    private volatile Role teacherRole;
    private volatile Role studentRole;
    private volatile Role userRole;

    public RoleManager(RoleRepository roleRepository, PrivilegeManager privilegeManager) {
        this.roleRepository = roleRepository;
        this.privilegeManager = privilegeManager;
    }

    @Transactional
    public void initialize() {
        adminRole = createOrUpdate(RoleName.ADMIN);
        teacherRole = createOrUpdate(RoleName.TEACHER);
        studentRole = createOrUpdate(RoleName.STUDENT);
        userRole = createOrUpdate(RoleName.USER);
    }

    private Role createOrUpdate(RoleName roleName) {
        Role role = roleRepository.findByName(roleName.name())
                .orElseGet(() -> new Role(roleName.name()));

        Set<Privilege> privileges = new HashSet<>();
        for (Privileges p : DEFINITIONS.get(roleName)) {
            privileges.add(privilegeManager.get(p));
        }
        role.setPrivileges(privileges); // keeps DB in sync if definitions change

        return roleRepository.save(role);
    }

    public Role getAdminRole() {return require(adminRole);}

    public Role getTeacherRole() {return require(teacherRole);}

    public Role getStudentRole() {return require(studentRole);}

    public Role getUserRole() {return require(userRole);}

    public Role getRole(RoleName name) {
        return switch (name) {
            case ADMIN -> getAdminRole();
            case TEACHER -> getTeacherRole();
            case STUDENT -> getStudentRole();
            case USER -> getUserRole();
        };
    }

    private Role require(Role role) {
        if (role == null) {
            throw new IllegalStateException("RoleManager is not initialized");
        }
        return role;
    }


//    private void createAdminUser() {
//
//        if (userRepository.existsByUsername("admin")) {
//            return;
//        }
//
//        Role adminRole = roleRepository.findByName("admin")
//                .orElseThrow(() ->
//                        new IllegalStateException(
//                                "Admin role was not created!"
//                        )
//                );
//
//        UserProfile admin = new UserProfile(
//                "admin",
//                passwordEncoder.encode("123"),
//                "ad@",
//                Collections.singletonList(adminRole)
//        );
//
//        userRepository.save(admin);
//    }
}