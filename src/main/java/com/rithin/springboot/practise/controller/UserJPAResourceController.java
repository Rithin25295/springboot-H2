package com.rithin.springboot.practise.controller;


import com.rithin.springboot.practise.exceptionhandling.UserNotFoundException;
import com.rithin.springboot.practise.model.Post;
import com.rithin.springboot.practise.model.User;
import com.rithin.springboot.practise.repository.PostRepository;
import com.rithin.springboot.practise.repository.UserRepository;
import com.rithin.springboot.practise.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;



@RestController
public class UserJPAResourceController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    //Get all users
    @GetMapping("/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();

    }
    //Get specific user
    @GetMapping("/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id -"+id);
        }



        //retrieve all users link
        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());

        resource.add(linkTo.withRel("All-users"));
        //HATEOAS
        return resource;
    }

//    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllUserPosts(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id - "+id);
        }
        return userOptional.get().getPosts();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/jpa/users/{id}/posts")
    public Post createUser(@PathVariable int id, @RequestBody Post post){
        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id - "+id);
        }
        User user = userOptional.get();

        post.setUser(user);

        postRepository.save(post);

        return post;
    }
}
