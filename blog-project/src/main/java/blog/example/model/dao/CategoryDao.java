package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import blog.example.model.entity.CategoryEntity;
import jakarta.transaction.Transactional;

public interface CategoryDao extends JpaRepository<CategoryEntity, Long> {
	
	CategoryEntity save(CategoryEntity categoryEntity);
	
	CategoryEntity findByCategoryId(Long categoryID);
	
	
	CategoryEntity findByCategoryName(String categoryName);
	
	List<CategoryEntity> findAll();
	
	//삭제 처리
	@Transactional
	List<CategoryEntity> deleteByCategoryId(Long categoryId);

}
