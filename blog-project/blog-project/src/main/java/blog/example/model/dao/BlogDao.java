package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blog.example.model.entity.BlogEntity;

public interface BlogDao extends JpaRepository<BlogEntity, Long> {
	//블로그 내용 보존
	BlogEntity save(BlogEntity blogEntity);
	
	//블로그 id로 DB에서 검색
	BlogEntity findByBlogId(Long blogId);
//	
//	@Query("SELECT b.blog_id, b.blog_title, b.blog_image, b.blog_comment, b.user_id, b.category_id,c.category_name From blog b"
//			+ "INNER JOIN account a ON b.user_id = a.user_id"
//			+ "INNER JOIN category c ON b.category_id = c.categor_id"
//			+ "WHERE b.user_id = ?")
	@Query(value = "SELECT b.blog_id, b.blog_title, b.blog_image, b.blog_comment, b.user_id, b.category_id, b.profile_image, b.profile " +
        "FROM blog b " +
        "INNER JOIN account a ON b.user_id = a.user_id " +
        "INNER JOIN category c ON b.category_id = c.category_id " +
        "WHERE b.user_id = ?1", nativeQuery = true)


    List<BlogEntity> findByUserSelectId(Long userId);
	
	//블로그 테이블의 모든 정보 습득
	List<BlogEntity>findAll();
	
	List<BlogEntity>findByUserId(Long userId);
	
	//카테고리 id로 DB에서 검색
	List<BlogEntity>findByCategoryId(Long categoryId);
}
