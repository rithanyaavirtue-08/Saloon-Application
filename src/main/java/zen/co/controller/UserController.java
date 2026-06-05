package zen.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zen.co.model.User;
import zen.co.repository.UserRepository;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")//create user
    public User createuser(@RequestBody User user){//new method
        return userRepository.save(user);//return created user
    }

    @GetMapping("/api/user")//getting User details
    public User getUser(){
        User user = new User();
        user.setEmail("rithanyaa.virtue@gmail.com");
        user.setFullName("Rithanyaa");
        user.setPhone("+91 6379155870");
        user.setRole("customer");
        return user;
    }
}
