package zen.co.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zen.co.exception.UserException;
import zen.co.model.User;
import zen.co.repository.UserRepository;
import zen.co.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> otp=userRepository.findById(id);
        if(otp.isPresent()){
            return otp.get();
        }
        throw new UserException("User not found with id: "+id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional <User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not found with id:"+id);
        }
        userRepository.deleteById(otp.get().getId());

    }


    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional <User> otp=userRepository.findById(id);
        if(otp.isEmpty()){
            throw new UserException("User not found with id:"+id);
        }
        User existingUser=otp.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());
        existingUser.setPhone(user.getPhone());
        return userRepository.save(existingUser);//update by id ,id is in existing user
    }
}
