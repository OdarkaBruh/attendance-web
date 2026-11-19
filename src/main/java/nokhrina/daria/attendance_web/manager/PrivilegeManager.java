package nokhrina.daria.attendance_web.manager;

import jakarta.transaction.Transactional;
import nokhrina.daria.attendance_web.enums.web.Privileges;
import nokhrina.daria.attendance_web.model.web.Privilege;
import nokhrina.daria.attendance_web.repo.web.PrivilegeRepository;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class PrivilegeManager {

    private final PrivilegeRepository privilegeRepository;
    private final Map<Privileges, Privilege> cache = new EnumMap<>(Privileges.class);

    public PrivilegeManager(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Transactional
    public void initialize() {
        for (Privileges p : Privileges.values()) {
            Privilege privilege = privilegeRepository.findByName(p.name())
                    .orElseGet(() -> privilegeRepository.save(new Privilege(p.name())));
            cache.put(p, privilege);
        }
    }

    public Privilege get(Privileges privilege) {
        Privilege result = cache.get(privilege);
        if (result == null) {
            throw new IllegalStateException("PrivilegeManager is not initialized");
        }
        return result;
    }
}