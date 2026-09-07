package com.kendirita.tour_user_role_service.dto;



import com.kendirita.tour_user_role_service.entity.Roles;
import com.kendirita.tour_user_role_service.entity.User;
import com.kendirita.tour_user_role_service.entity.UserRole;

import java.util.Date;

public class UserRoleResponse {

    private String id;
    private Roles role;
    private User user;


    public static UserRoleResponse from(UserRole userRole) {

        UserRoleResponse dto = new UserRoleResponse();
        dto.id = userRole.getId();
        dto.role = userRole.getRole();
        dto.user=userRole.getUser();


        return dto;
    }

    public String getId() {
        return id;
    }

    public Roles getRole() {
        return role;
    }

    public User getUser() {
        return user;
    }
}