package com.kendirita.tour_user_role_service.controller;

import com.kendirita.tour_user_role_service.dto.UserRoleResponse;
import com.kendirita.tour_user_role_service.entity.User;
import com.kendirita.tour_user_role_service.entity.UserRole;
import com.kendirita.tour_user_role_service.repository.UserRoleRepository;
import com.kendirita.tour_user_role_service.response.ResponseHandler;
import com.kendirita.tour_user_role_service.service.UserRoleService;
import com.kendirita.tour_user_role_service.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v2/tour")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/users/user-role")
    public ResponseEntity<Object> createUserRole(@RequestBody UserRole userRole) {
        UserRole createdUserRole = userRoleService.createUserRole(userRole);
        if(createdUserRole == null){
            return ResponseHandler.generateResponse(UUID.randomUUID(), "UserRole already exists", HttpStatus.CONFLICT, null, TimestampUtil.now()
            );
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(), "UserRole created", HttpStatus.CREATED, UserRoleResponse.from(createdUserRole),TimestampUtil.now());
    }

    @GetMapping("/users/user-role/{email}")
    public ResponseEntity<Object> searchByEmail(@PathVariable String email){
        UserRole userRoleEmail = userRoleService.searchByEmail(email);
        if(userRoleEmail==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"User not found",HttpStatus.NOT_FOUND,null,TimestampUtil.now());
        }
        return ResponseHandler.generateResponse(UUID.randomUUID(),"User details found",HttpStatus.OK, UserRoleResponse.from(userRoleEmail),TimestampUtil.now());
    }

    @GetMapping("/users/user-role")
    public ResponseEntity<Object> listUserRole(){
        List<UserRole> userRole = userRoleService.listUsers();
        List<UserRoleResponse> userRoleResponse =userRole.stream().map(UserRoleResponse::from).toList();
        return ResponseHandler.generateResponse(UUID.randomUUID(),"Users Roles found",HttpStatus.OK, userRoleResponse,TimestampUtil.now());
    }

    @PutMapping("/users/user-role/{email}")
    public ResponseEntity<Object> updateUserRole(@RequestBody UserRole userRole, @PathVariable String email){
        UserRole currentUserRole = userRoleService.searchByEmail(email);
        if(currentUserRole==null){
            return ResponseHandler.generateResponse(UUID.randomUUID(),"UserRole not found",HttpStatus.NOT_FOUND,"",TimestampUtil.now());
        }
        currentUserRole.setUser(userRole.getUser());

        //update existing user profile
//        if(currentUserRole != null) {
//            currentUser.getProfile().setPhone(user.getProfile().getPhone());
//            currentUser.getProfile().setAvatarUrl(user.getProfile().getAvatarUrl());
//        }
        UserRole updatedUser = userRoleRepository.save(currentUserRole);
        return ResponseHandler.generateResponse(UUID.randomUUID(),"UserRole updated",HttpStatus.OK, UserRoleResponse.from(updatedUser),TimestampUtil.now());
    }

    @DeleteMapping("/users/user-role/{email}")
    public ResponseEntity<Object> deleteUserRoleByEmail(@PathVariable String email) {

        boolean deleted = userRoleService.deleteByEmail(email);

        if (!deleted) {
            return ResponseHandler.generateResponse(UUID.randomUUID(), "UserRole not found", HttpStatus.NOT_FOUND, null, TimestampUtil.now());
        }

        return ResponseHandler.generateResponse(UUID.randomUUID(), "UserRole deleted successfully", HttpStatus.NO_CONTENT, null, TimestampUtil.now());
    }
}
