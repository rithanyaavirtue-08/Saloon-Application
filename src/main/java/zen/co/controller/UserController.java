package zen.co.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zen.co.model.User;
import zen.co.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }


    @GetMapping("/api/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/api/users/{userId}")
    public User getUserById(@PathVariable("userId")  long id) throws Exception {
        Optional<User> otp=userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }
        throw new Exception("User not found with id: "+id);
    }

     @PutMapping("/api/users/{id}")
    public User updateUser(@RequestBody User user,@PathVariable long id) throws Exception {
        Optional <User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new Exception("User not found with id:"+id);
        }
        User existingUser=otp.get();
        existingUser.setFullName(user.getFullName() );
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);//update by id ,id is in existing user
     }

}
