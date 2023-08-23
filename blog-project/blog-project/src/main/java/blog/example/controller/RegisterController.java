package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import blog.example.service.UserService;


@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	//등록 화면 표시
	@GetMapping("/register")
	public String getRegisterPage() {

		return "register.html";
	}
	
	//유저 정보 습득
	@PostMapping("/register/process")
	public String register(@RequestParam String userName, @RequestParam String userEmail, @RequestParam String userPassword) {
		//존재하지 않는 경우엔 보존 후 로그인 화면으로 이동
		if(userService.createAccount(userName, userEmail, userPassword)) {
			return "redirect:/login";
		}else {
			return "register.html";
		}
	}
}
