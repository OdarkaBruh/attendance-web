package nokhrina.daria.attendance_web.config;

import nokhrina.daria.attendance_web.manager.PrivilegeManager;
import nokhrina.daria.attendance_web.manager.RoleManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SecurityDataInitializer implements ApplicationRunner {

    private final PrivilegeManager privilegeManager;
    private final RoleManager roleManager;

    public SecurityDataInitializer(PrivilegeManager privilegeManager, RoleManager roleManager) {
        this.privilegeManager = privilegeManager;
        this.roleManager = roleManager;
    }

    @Override
    public void run(ApplicationArguments args) {
        privilegeManager.initialize();
        roleManager.initialize();
    }
}