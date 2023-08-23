package blog.example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name="blog")
public class BlogEntity {
	
	@Id
	@Column(name="blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long blogId;
	
	@NonNull
	@Column(name="blog_title")
	private String blogTitle;
	
	@Column(name="blog_image")
	private String blogImage;
	
	@Column(name="blog_comment")
	private String blogComment;
	
	@Column(name="user_id")
	private Long userId;

	@Column(name="category_id")
	private Long categoryId;
	
	@Column(name="profile_image")
	private String profileImage;
	
	@Column(name="profile")
	private String profile;
	
	

	public BlogEntity(@NonNull String blogTitle, String blogImage, String blogComment, Long userId, Long categoryId,
			String profileImage, String profile) {
		this.blogTitle = blogTitle;
		this.blogImage = blogImage;
		this.blogComment = blogComment;
		this.userId = userId;
		this.categoryId = categoryId;
		this.profileImage = profileImage;
		this.profile = profile;
	}


	public BlogEntity() {
		
	}


	public Long getBlogId() {
		return blogId;
	}


	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}


	public String getBlogTitle() {
		return blogTitle;
	}


	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}


	public String getBlogImage() {
		return blogImage;
	}


	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}


	public String getBlogComment() {
		return blogComment;
	}


	public void setBlogComment(String blogComment) {
		this.blogComment = blogComment;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}


	public String getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public String getProfile() {
		return profile;
	}


	public void setProfile(String profile) {
		this.profile = profile;
	}


	

}
