package com.pikake.api.pikakeapi.entiy;

public enum Role {
    ROLE_STUDENT, ROLE_TEACHER, ROLE_PARENT, ROLE_ADMIN;

//    public String getAuthority() {
//        return Role.ROLE_PARENT.name();
//    }
//
//    public Collection<GrantedAuthority> getAllowedOperations() {
//        return allowedOperations;
//    }

    // return role for specific id
//    public static Role getRoleForId(final int id)
//    {
//        for (final Role role : values())
//            if (role.id == id)
//                return role;
//        return null;
//    }
}