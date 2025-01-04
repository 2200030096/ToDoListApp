package User;

import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl {
  
	private UserRepositary ur;
	
	public boolean authenticate(String username, String password)
	{
		User user = ur.findByUsername(username);
		return user != null && user.getPassword().equals(password);
	}
}
