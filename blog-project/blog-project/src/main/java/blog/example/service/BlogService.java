package blog.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import blog.example.model.dao.BlogDao;
import blog.example.model.dao.CategoryDao;
import blog.example.model.entity.BlogEntity;
import blog.example.model.entity.CategoryEntity;

@Service
public class BlogService {
	
	@Autowired
	private BlogDao blogDao;
	
	//블로그 내용 보존
	public void insert(String blogTitle, String blogImage, String blogComment, Long userId, Long categoryId,
			String profileImage, String profile) {
		blogDao.save(new BlogEntity(blogTitle, blogImage, blogComment, userId, categoryId, profileImage, profile));
	}
	//유저 블로그 일람
	public List<BlogEntity> selectByAll(){
		return blogDao.findAll();
	}
	
	//블로그 일람
	public List<BlogEntity> selectByBlogId(Long blogId){
	return blogDao.findByUserId(blogId);
}
	
	//내용 갱신
	public void update(Long blogId, String blogTitle, String blogImage, String blogComment, Long userId, Long categoryId,
			String profileImage, String profile) {
		blogDao.save(new BlogEntity(blogTitle, blogImage, blogComment, userId, categoryId, profileImage, profile));
	}

	//카테고리 일람
	public List<BlogEntity> selectByCategoryId(Long categoryId){
		return blogDao.findByCategoryId(categoryId);
	}
	
	//テーブル結合情報を持ってくる
	public List<BlogEntity> selectBlogList(Long userId){
		return blogDao.findByUserSelectId(userId);
	}

}
