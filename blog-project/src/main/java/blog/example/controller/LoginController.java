package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.model.entity.UserEntity;
import blog.example.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
	
	@PostMapping("/login/process")
	public String login(@RequestParam String userName, @RequestParam String userPassword ) {
		UserEntity user = userService.chackLogin(userName, userPassword);
		
		if(user == null) {
			return "redirect:/login";
		}else {
			session.setAttribute("user", user);
			return "redirect:/blog/main";
		}
	}


	
}
