package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //any time you are saving something to the database you should include the @Transactional annotation which ensures that the transaction that gets opened with your datasource gets resolved
    //this method is to add user

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        //this step is where a user is actually people persisted***
        userRepository.saveAndFlush(user);
        response.add("login.html");
        return response;
    }



    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        System.out.println(userOptional + "--this is userOptional");
        System.out.println(userDto + "--- this is userDto");
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("home.html");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username or password incorrect");
            }
        } else{
            response.add("Username or password incorrect");
        }
        return response;
    }
}
