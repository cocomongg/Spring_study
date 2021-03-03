package com.example.restful.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrivedAllUsers(){
        return service.findAll();
    }

    // GET /users/1 -> 1은 string이지만 int로 함수에서 받으면 int로 자동캐스팅 됨
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    /**
     * Post, Delete, Get, Put 등에 맞게 각각의 응답코드를 구분 하는게 좋음
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){ //json같은 데이터를 클라이언트로 부터 받기 위해서 @RequestBody선언해야 함
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()//요청에 따른 결과값을 다르게 반환하는게 좋은 rest api 설계법
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build(); // status code: 201 created
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }
}
