package co.develhope.redis1.controllers;

import co.develhope.redis1.entities.jpa.UserJPA;
import co.develhope.redis1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public UserJPA create(@RequestBody UserJPA user) {
        return userService.create(user);
    }

    @GetMapping
    public List<UserJPA> readAll() {
        return userService.readAll();
    }


    @GetMapping("/{id}")
    public Object readOne(@PathVariable Long id){
        return userService.readOne(id);
    }


    @PutMapping("/{id}")
    public UserJPA update(@PathVariable Long id, @RequestBody UserJPA user){
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }

    @DeleteMapping
    public void deleteAll(){
        userService.deleteAll();
    }


}
