package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.User;
import com.codegym.backendjavasocialnetwork.entity.dto.UserInfoForm;
import com.codegym.backendjavasocialnetwork.entity.dto.UserPrinciple;
import com.codegym.backendjavasocialnetwork.repository.UserRepository;
import com.codegym.backendjavasocialnetwork.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void remove(Long id) {
        repository.findById(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrinciple.build(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public void updateUser(UserInfoForm userInfoForm, User user) {
        user.setFullName(userInfoForm.getFullName());
        user.setEmail(userInfoForm.getEmail());
        user.setDateOfBirth(userInfoForm.getDateOfBirth());
        user.setAddress(userInfoForm.getAddress());
        user.setAvatarUrl(userInfoForm.getAvatarUrl());
        user.setHobbies(userInfoForm.getHobbies());
        user.setPhone(userInfoForm.getPhone());
    }

    @Override
    public UserInfoForm getUserInfo(User user) {
        return new UserInfoForm(user.getFullName(),user.getAddress(),user.getHobbies()
                ,user.getAvatarUrl(), user.getCoverImgUrl(), user.getEmail(),user.getPhone(),user.getDateOfBirth());
    }

    @Override
    public Iterable<User> findAllByUserNameContaining(String fullName) {
        return repository.findAllByUsernameContaining(fullName);
    }
}