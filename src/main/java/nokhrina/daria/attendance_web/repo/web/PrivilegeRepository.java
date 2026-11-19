package nokhrina.daria.attendance_web.repo.web;


import nokhrina.daria.attendance_web.model.web.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    Optional<Privilege> findByName(String name);
    boolean existsByName(String name);
}