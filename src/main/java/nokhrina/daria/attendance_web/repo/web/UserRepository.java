package nokhrina.daria.attendance_web.repo.web;

import nokhrina.daria.attendance_web.model.web.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);
    Optional<UserProfile> findByUsername(String username);

    boolean existsByUsername(String admin);
}

