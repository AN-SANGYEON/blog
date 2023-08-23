package blog.example.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	public String register(@RequestParam String userName, @RequestParam String userEmail, 
			@RequestParam String userPassword,
			@RequestParam MultipartFile profileImage,
			@RequestParam MultipartFile blogImage,
			@RequestParam String profile) {
		//プロファイル画像のファイル名の取得とアップロード処理
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + profileImage.getOriginalFilename();
		try {
			Files.copy(profileImage.getInputStream(), Path.of("src/main/resources/static/blog-image/" + fileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//ブログ画像のファイル名の取得とアップロード処理
		String blogFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-image/" + blogFileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//존재하지 않는 경우엔 보존 후 로그인 화면으로 이동
		if(userService.createAccount(userName, userEmail, userPassword,fileName,blogFileName,profile)) {
			return "redirect:/login";
		}else {
			return "register.html";
		}
	}
}
