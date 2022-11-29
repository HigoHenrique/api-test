package com.api.librarystudio.domain.service;


import com.api.librarystudio.domain.model.User;
import com.api.librarystudio.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return findOrFail(id);
    }

    public User save(User user){
        user.getBooks().forEach(book -> book.setUser(user));
        return userRepository.save(user);
    }

    public User update(Long id, User user){
        User userSave = findOrFail(id);

        userSave.getBooks().clear();
        userSave.getBooks().addAll(user.getBooks());

        BeanUtils.copyProperties(user, userSave, "id", "books");
        return userRepository.save(userSave);
    }

    public void delete(Long id){
        User user = findOrFail(id);
        userRepository.delete(user);
    }

    private User findOrFail(long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(("Usuário não encontrado!")));
    }
}
