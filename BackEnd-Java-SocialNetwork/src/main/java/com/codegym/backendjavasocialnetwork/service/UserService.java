package com.codegym.backendjavasocialnetwork.service;

import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.dto.UserInfoForm;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Optional;

public interface UserService extends GenericService<User>, UserDetailsService {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Iterable<User> getAllUsers();
    Boolean existsByEmail(String email);
    void updateUser(UserInfoForm userInfoForm, User user);
    UserInfoForm getUserInfo(User user);
    Iterable<User> getMutualFriendsList(Long uid, Long id);
    Iterable<User> getListRequestToMe(Long uid);
}