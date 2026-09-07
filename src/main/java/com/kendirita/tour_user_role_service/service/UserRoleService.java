package com.kendirita.tour_user_role_service.service;

import com.kendirita.tour_user_role_service.entity.Profile;
import com.kendirita.tour_user_role_service.entity.Roles;
import com.kendirita.tour_user_role_service.entity.User;
import com.kendirita.tour_user_role_service.entity.UserRole;
import com.kendirita.tour_user_role_service.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;

    //create new user
    @Transactional
    public UserRole createUserRole(UserRole userRole) {

//        if (userRoleRepository.existsByEmail(userRole.getUser().getEmail())) {
        if (userRoleRepository.existsByEmail(userRole.getEmail())) {
            throw new IllegalStateException("UserRole  with this Email already exists");
        }

//        Profile profile = userRole.;
//
//        if (profile != null) {
//            profile.setUser(user);
//            profile.setFullName(user.getFullName());
//            profile.setEmail(user.getEmail());
//        }

//        UserRole ur = user.getUserRole();
//        if (ur != null){
//            userRole.setUser(user);
//            user.setUserRole(userRole);
//        }
        if (userRole.getEmail() !=null){
            userRole.setRole(userRole.getRole());
        }

        return userRoleRepository.save(userRole);
    }

    //search user by email
    public UserRole searchByEmail(String email){
        return userRoleRepository.searchByEmail(email);
    }

    //fetch all users
    public List<UserRole> listUsers(){
        return userRoleRepository.findAll();
    }

    public boolean deleteByEmail(String email) {
        Optional<UserRole> userR = Optional.ofNullable(userRoleRepository.searchByEmail(email));
        if (userR.isEmpty()) {
            return false;
        }
        userRoleRepository.delete(userR.get());
        return true;
    }
}
