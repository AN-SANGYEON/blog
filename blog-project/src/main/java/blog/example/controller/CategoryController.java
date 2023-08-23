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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.model.entity.CategoryEntity;
import blog.example.model.entity.PostEntity;
import blog.example.model.entity.UserEntity;
import blog.example.service.CategoryService;
import jakarta.servlet.http.HttpSession;


@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/category/add")
	public String getCategoryPage() {
		return "category.html";
	}
	
	@PostMapping("/category/add")
	public String category(@RequestParam String categoryName, @RequestParam MultipartFile image) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + image.getOriginalFilename();
		try {
			// 파일 업로드 처리
			Files.copy(image.getInputStream(), Path.of("src/main/resources/static/category-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(categoryService.createCategory(categoryName,fileName)) {
			
			return "redirect:/blog/edit";
		}else {
			return "redirect:/category/add";
		}
		
	}
	
	//카테고리 편집 화면
	@GetMapping("/category/edit/{categoryId}")
	public String getCategoryEditPage(@PathVariable Long categoryId,Model model) {
		
		UserEntity userList = (UserEntity) session.getAttribute("user");
		
		CategoryEntity categoryEntity = categoryService.selectCategoryId(categoryId);
		model.addAttribute("category", categoryEntity);
		
	    return "category_edit.html";

	}
	
	@PostMapping("/category/update")
	//수정
	public String updateCategory(@RequestParam Long categoryId, String categoryName,
			MultipartFile categoryImage, Model model) {

		UserEntity userList = (UserEntity) session.getAttribute("user");
		Long userId = userList.getUserId();

		// 파일 이름 습득
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date()) + categoryImage.getOriginalFilename();
		try {
			// 파일 업로드 처리
			Files.copy(categoryImage.getInputStream(), Path.of("src/main/resources/static/category-img/" + fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}

		categoryService.update(categoryId, categoryName, fileName);
		return "redirect:/post/list/" + categoryId;


	}
	
}
