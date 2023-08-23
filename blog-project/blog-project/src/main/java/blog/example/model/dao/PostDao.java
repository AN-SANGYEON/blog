package blog.example.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import blog.example.model.entity.PostEntity;
import jakarta.transaction.Transactional;

public interface PostDao extends JpaRepository<PostEntity, Long> {
	
	PostEntity save(PostEntity postEntity);
	
	PostEntity findByPostId(Long postId);
	
	@Query(value = "SELECT p.post_id, p.post_title, p.post_image, p.post_comment, p.category_id FROM post p"
			+ "INNER JOIN category c ON p.category_id = c.category_id"
			+ "WHERE c.category_id =?1", nativeQuery = true)
	
	List<PostEntity> findAll();
	
	//삭제 
	@Transactional
	List<PostEntity> deleteByPostId(Long postId);
}
