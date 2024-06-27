package thuctap.task4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import thuctap.task4.model.User;
import thuctap.task4.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public String addUser(@RequestBody User user) throws Exception {
        try {
            userService.addNewUser(user);
            return "User added successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to add user: " + e.getMessage();
        }
    }
    @PutMapping("/{id}")
    public String addUser(@RequestBody User user,@PathVariable int id) throws Exception {
        try {
            userService.updateUser(user,id);
            return "User updated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to update user: " + e.getMessage();
        }
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) throws Exception {
        userService.deleteUser(id);
        return "Delete successfully";
    }
    @GetMapping("/name")
    public List<User> findByName(@RequestParam String name){
        return userService.searchByName(name);
    }
    @GetMapping("/address")
    public List<User> findByAddree(@RequestParam String addr){
        return userService.searchByAddr(addr);
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService.searchById(id);
    }
    @GetMapping("/all")
    public List<User> sxTen(){
        return userService.sx();
    }

}
