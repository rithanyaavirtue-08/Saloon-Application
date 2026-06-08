package zen.co.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zen.co.exception.UserException;
import zen.co.model.User;
import zen.co.repository.UserRepository;
import zen.co.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class  UserController {
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;


    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody @Valid  User user){
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }


    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId")  long id) throws Exception {
           User users=userService.getUserById(id);
           return new ResponseEntity<>(users,HttpStatus.OK);
    }

     @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid  User user,@PathVariable long id) throws Exception {
         User users=userService.updateUser(id,user);
           return new ResponseEntity<>(users,HttpStatus.OK);
     }

     @DeleteMapping("/api/users/{id}")
     public ResponseEntity<String>  deleteUserById(@PathVariable Long id) throws Exception {
         userService.deleteUser(id);
         return new ResponseEntity<>("User deleted successfully",HttpStatus.ACCEPTED);
     }

}
