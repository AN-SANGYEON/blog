package blog.example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NonNull;

@Entity
@Table(name="account")
public class UserEntity {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@NonNull
	@Column(name="user_name")
	private String userName;
	
	@NonNull
	@Column(name="user_email")
	private String userEmail;
	
	@NonNull
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="profile_image")
	private String profileImage;
	
	@Column(name="blog_image")
	private String blogImage;
	
	@Column(name="profile")
	private String profile;
	
	public UserEntity(){
		
	}

	
	public UserEntity(Long userId, @NonNull String userName, @NonNull String userEmail, @NonNull String userPassword,
			String profileImage, String blogImage, String profile) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.profileImage = profileImage;
		this.blogImage = blogImage;
		this.profile = profile;
	}


	public UserEntity(@NonNull String userName, @NonNull String userEmail, @NonNull String userPassword,
			String profileImage, String blogImage, String profile) {
		
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.profileImage = profileImage;
		this.blogImage = blogImage;
		this.profile = profile;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getBlogImage() {
		return blogImage;
	}

	public void setBlogImage(String blogImage) {
		this.blogImage = blogImage;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	
	
	

	


}
