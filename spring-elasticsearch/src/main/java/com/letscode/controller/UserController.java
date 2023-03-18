package com.letscode.controller;

import com.letscode.entity.User;
import com.letscode.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    @PostConstruct
    public void init(){
        User user = new User();
        user.setName("Sam");
        user.setSurname("taylor");
        user.setAddress("localhost");
        user.setDob(Calendar.getInstance().getTime());
        userRepository.save(user);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable String search) {

        List<User> users = userRepository.findByNameLikeOrSurnameLike(search, search);
        return ResponseEntity.ok(users);

    }
}
