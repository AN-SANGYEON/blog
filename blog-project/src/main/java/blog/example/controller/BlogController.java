package blog.example.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.example.service.CategoryService;
import blog.example.service.UserService;
import jakarta.servlet.http.HttpSession;
import blog.example.model.entity.CategoryEntity;
import blog.example.model.entity.UserEntity;

@Controller
public class BlogController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired 
	private HttpSession session;
	
	
	@GetMapping("/blog/main")
	public String getBlogListPage(Model model) {
		/**
		 * セッションから現在のユーザー情報を取得するため、sessionオブジェクトを使用しています。
		 **/
		UserEntity userList = (UserEntity) session.getAttribute("user");
		Long userId = userList.getUserId();
		List<CategoryEntity> categoryList = categoryService.findByAll();
		model.addAttribute("categoryList", categoryList);
		UserEntity user = userService.findUser(userList.getUserId());
		model.addAttribute("mainImage", user.getBlogImage());
		model.addAttribute("profileImage", user.getProfileImage());
		model.addAttribute("profile",user.getProfile());
		return "blog.html";
	}

}
