package User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary  extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
