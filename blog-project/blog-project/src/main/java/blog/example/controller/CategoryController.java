package blog.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.example.service.CategoryService;


@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	@GetMapping("/category")
	public String getCategoryPage() {
		return "category.html";
	}
	
	@PostMapping("/category/process")
	public String category(@RequestParam String categoryName, @RequestParam MultipartFile image) {
		if(categoryService.createCategory(categoryName)) {
			return "redirect:/edit";
		}else {
			return "redirect:/category";
		}
		
	}

}
