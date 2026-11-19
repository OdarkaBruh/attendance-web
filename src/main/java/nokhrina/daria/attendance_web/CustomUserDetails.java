package nokhrina.daria.attendance_web;

import nokhrina.daria.attendance_web.model.web.Privilege;
import nokhrina.daria.attendance_web.model.web.Role;
import nokhrina.daria.attendance_web.model.web.UserProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserProfile user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = buildAuthorities(user.getRoles());
    }

    private static Collection<GrantedAuthority> buildAuthorities(Collection<Role> roles) {
        Set<GrantedAuthority> result = new HashSet<>();
        for (Role role : roles) {
            result.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            for (Privilege privilege : role.getPrivileges()) {
                result.add(new SimpleGrantedAuthority(privilege.getName()));
            }
        }
        return result;
    }

    public Long getId() { return id; }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return username; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}