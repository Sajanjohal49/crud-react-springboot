package com.sajandeep.crudReactSpring.controller;

import com.sajandeep.crudReactSpring.exception.UserNotFoundException;
import com.sajandeep.crudReactSpring.model.User;
import com.sajandeep.crudReactSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }
    @GetMapping(value = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }
     @PutMapping("user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map((user)-> {
                            user.setUsername(newUser.getUsername());
                            user.setName(newUser.getName());
                            user.setEmail(newUser.getEmail());
                            return userRepository.save(user);

                        }).orElseThrow(()-> new UserNotFoundException(id));

 }
 @DeleteMapping("user/{id}")
String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return  " The Employee with the Id "+id+" has been deleted successfully";

 }
}
