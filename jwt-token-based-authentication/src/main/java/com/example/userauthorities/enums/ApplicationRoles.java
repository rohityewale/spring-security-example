package com.example.userauthorities.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.userauthorities.enums.ApplicationPermissions.*;


public enum ApplicationRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE
            , STUDENT_READ, STUDENT_WRITE)),
    ADMIN_TRAINEE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationPermissions> applicationPermissionsSet;

    ApplicationRoles(Set<ApplicationPermissions> applicationPermissionsSet) {
        this.applicationPermissionsSet = applicationPermissionsSet;
    }

    public Set<ApplicationPermissions> getApplicationPermissionsSet() {
        return applicationPermissionsSet;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(){
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = getApplicationPermissionsSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return simpleGrantedAuthorities;
    }
}
