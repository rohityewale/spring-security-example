package com.example.usersandroles.enums;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.usersandroles.enums.ApplicationPermissions.*;

public enum ApplicationRoles {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE
            , STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationPermissions> applicationPermissionsSet;

    ApplicationRoles(Set<ApplicationPermissions> applicationPermissionsSet) {
        this.applicationPermissionsSet = applicationPermissionsSet;
    }

    public Set<ApplicationPermissions> getApplicationPermissionsSet() {
        return applicationPermissionsSet;
    }
}
