package com.restfulapi.restfulwebservices.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.restfulapi.restfulwebservices.Exception.UserNotFoundException;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    
    @Autowired
    private UserDaoService service;

    @GetMapping(path = "/hello")
    public String helloWorld(){
      return "Hello World";
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
      return service.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public EntityModel<User> retrieveOneUser(@PathVariable int id){

      User user = service.findOne(id);
      if(user == null) {
        throw new UserNotFoundException("id=" + id);
      }
      EntityModel<User> model = EntityModel.of(user);

      WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());

      model.add(link.withRel("all-users"));

      return model;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> setUser(@Valid @RequestBody User user){
      User savedUser = service.save(user);
      URI location =ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(savedUser.getId()).toUri();

      return ResponseEntity.created(location).build();

    }

    @DeleteMapping(path="users/{id}")
    public void deleteUser(@PathVariable int id){
      User user = service.deleteById(id);
      if(user == null) {
        throw new UserNotFoundException("id=" + id);
      }
    }

}
