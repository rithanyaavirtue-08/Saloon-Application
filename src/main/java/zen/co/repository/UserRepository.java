package zen.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zen.co.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
}
