package blog.example.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.model.entity.CategoryEntity;
import blog.example.model.entity.UserEntity;
import blog.example.service.CategoryService;
import blog.example.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EditController {
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/blog/edit")
	public String getEditPage(Model model) {
		UserEntity userList = (UserEntity) session.getAttribute("user");
		model.addAttribute("userId", userList.getUserId());
		List<CategoryEntity> categoryList = categoryService.findByAll();
		
		UserEntity user = userService.findUser(userList.getUserId());
		model.addAttribute("categoryList",  categoryList);
		model.addAttribute("mainImage", user.getBlogImage());
		model.addAttribute("profileImage", user.getProfileImage());
		model.addAttribute("profile",user.getProfile());
		return "edit.html";
	}
	@PostMapping("/update/main/image")
	public String updateMainImage(
			@RequestParam Long userId,
			@RequestParam MultipartFile blogImage
			) {
		
		//ブログ画像のファイル名の取得とアップロード処理
		String blogFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + blogImage.getOriginalFilename();
		try {
			Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-image/" + blogFileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//존재하지 않는 경우엔 보존 후 로그인 화면으로 이동
		if(userService.updateBlogImage(userId, blogFileName)) {
			return "redirect:/blog/main";
		}else {
			return "redirect:/blog/edit";
		}
	}
	
	@PostMapping("/update/profile/image")
	public String updateProfile(
			@RequestParam Long userId,
			@RequestParam MultipartFile profileImage
			) {
		
		//ブログ画像のファイル名の取得とアップロード処理
		String profileFileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + profileImage.getOriginalFilename();
		try {
			Files.copy(profileImage.getInputStream(), Path.of("src/main/resources/static/blog-image/" + profileFileName));
		}catch(Exception e) {
			e.printStackTrace();
		}
		//존재하지 않는 경우엔 보존 후 로그인 화면으로 이동
		if(userService.updateProfileImage(userId, profileFileName)) {
			return "redirect:/blog/main";
		}else {
			return "redirect:/blog/edit";
		}
	}
	
	@PostMapping("/update/profile")
	public String updateProfile(
			@RequestParam Long userId,
			@RequestParam String profile
			) {
		//존재하지 않는 경우엔 보존 후 로그인 화면으로 이동
		if(userService.updateProfile(userId, profile)) {
			return "redirect:/blog/main";
		}else {
			return "redirect:/blog/edit";
		}
	}
	
	

}
