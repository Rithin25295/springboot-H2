package com.rithin.springboot.practise.controller;


import com.rithin.springboot.practise.exceptionhandling.UserNotFoundException;
import com.rithin.springboot.practise.model.User;
import com.rithin.springboot.practise.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
public class UserResourceController {




    @Autowired
    UserDaoService userService;

    //Get all users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.finadAll();

    }
    //Get specific user
    @GetMapping("/users/{id}")
    public Resource<User> getUser(@PathVariable int id){
        User user =userService.findUser(id);
        if(user==null){
            throw new UserNotFoundException("id -"+id);
        }


        //retrieve all users link
        Resource<User> resource = new Resource<User>(user);

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("All-users"));
        //HATEOAS
        return resource;
    }

//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.deleteUser(id);
        if(user == null){
            throw new UserNotFoundException("id - "+id);
        }
    }
}
