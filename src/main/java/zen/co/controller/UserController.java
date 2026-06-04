package zen.co.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zen.co.model.User;

@RestController
public class UserController {
    @GetMapping("/api/user")
    public User getUser(){
        User user = new User();
        user.setEmail("rithanyaa.virtue@gmail.com");
        user.setFullName("Rithanyaa");
        user.setPhone("+91 6379155870");
        user.setRole("customer");
        return user;
    }
}
