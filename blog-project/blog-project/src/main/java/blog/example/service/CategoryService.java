package blog.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.CategoryDao;
import blog.example.model.entity.CategoryEntity;

@Service
public class CategoryService {

	@Autowired
	private CategoryDao categoryDao;
	
	//일람표시
	public List<CategoryEntity> findByAll(){
		return categoryDao.findAll();
	}
	
	//내용보존
	public void insert(String categoryName) {
		categoryDao.save(new CategoryEntity(categoryName));
	}
	
	//편집
	public void update(Long categoryId, String categoryName) {
		categoryDao.save(new CategoryEntity(categoryId, categoryName));
	}
	
	//카테고리 ID로 DB에서 검색
	public CategoryEntity selectCategoryId(Long categoryId) {
		return categoryDao.findByCategoryId(categoryId);
	}
	
	public boolean createCategory(String categoryName) {
		CategoryEntity categoryEntity = categoryDao.findByCategoryName(categoryName);
		if(categoryEntity == null) {
			categoryDao.save(new CategoryEntity(categoryName));
			return true;
		}else {
			return false;
		}
	}
	
	//삭제
	public void delete(Long categoryId) {
		categoryDao.deleteById(categoryId);
	}
}
