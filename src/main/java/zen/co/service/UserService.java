package zen.co.service;

import org.w3c.dom.stylesheets.LinkStyle;
import zen.co.exception.UserException;
import zen.co.model.User;

import java.util.List;

public interface UserService {
    public User createUser(User user);
   public User getUserById(Long id) throws UserException;
   public List<User> getAllUsers();
   public void deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;
}
