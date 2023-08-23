package blog.example.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.model.entity.CategoryEntity;
import blog.example.model.entity.PostEntity;
import blog.example.model.entity.UserEntity;
import blog.example.service.BlogService;
import blog.example.service.PostService;
import blog.example.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class PostController {
	@Autowired
	private UserService userService;

	@Autowired
	PostService postService;

	@Autowired
	private HttpSession session;

	@GetMapping("/post/register")
	public String getPostRegisterPage(Model model) {
		/**
		 * セッションから現在のユーザー情報を取得するため、sessionオブジェクトを使用しています。
		 **/
		UserEntity userList = (UserEntity) session.getAttribute("user");

		return "post.html";
	}

	@PostMapping("/post/register")
	//수정
	public String blogRegister(@RequestParam String title, @RequestParam Long categoryId,
			@RequestParam MultipartFile image, 
			@RequestParam String postComment, Model model) {

		UserEntity userList = (UserEntity) session.getAttribute("user");
		Long userId = userList.getUserId();

		// 파일 이름 습득
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + image.getOriginalFilename();
		try {
			// 파일 업로드 처리
			Files.copy(image.getInputStream(), Path.of("src/main/resources/static/blog-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		postService.insert(title, fileName, postComment, categoryId);
		return "register.html";

	}
	
	@GetMapping("/post/list")
	public String getPostListPage(Model model) {
		CategoryEntity categoryList = (CategoryEntity) session.getAttribute("category");
		Long categoryId = categoryList.getCategoryId();
		List<PostEntity> postList = postService.findByAll();
		return "post_list.html";
	}
}
