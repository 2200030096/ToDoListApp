package User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
   
	@Autowired
	private UserDAOImpl ud;
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password , Model model)
	{
		boolean IsAuthenticated = ud.authenticate(username,password);
		if(IsAuthenticated)
			return "/home";
		else
			model.addAttribute("error","Invalid username or password");
		return "login";
	}
}
