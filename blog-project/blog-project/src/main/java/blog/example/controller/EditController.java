package blog.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditController {
	
	@GetMapping("/blog/edit")
	public String getEditPage() {
		return("edit.html");
	}
	
	

}
