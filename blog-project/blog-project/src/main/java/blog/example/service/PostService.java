package blog.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.example.model.dao.PostDao;
import blog.example.model.entity.PostEntity;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	//일람표시
	public List<PostEntity> findByAll(){
		return postDao.findAll();
	}
	
	//내용 보존
	public void insert(String postTitle, String postImage, String postComment, Long categoryId) {
		postDao.save(new PostEntity(postTitle, postImage, postComment, categoryId));
	}
	
	//편집
	public void update(Long postId, String postTitle, String postImage, String postComment,Long categoryId) {
		postDao.save(new PostEntity(postId, postTitle, postImage, postComment, categoryId));
	}
	
	public PostEntity selectPostId(Long postId) {
		return postDao.findByPostId(postId);
	}
	
	
	//삭제
	public void delete(Long postId) {
		postDao.deleteByPostId(postId);
	}
	

}
