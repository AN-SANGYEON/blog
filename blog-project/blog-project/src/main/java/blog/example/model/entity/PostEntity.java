package blog.example.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "post")
public class PostEntity {
	
	@Id
	@Column(name="post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long postId;
	
	@NonNull
	@Column(name="post_title")
	private String postTitle;
	
	@Column(name="post_image")
	private String postImage;
	
	@Column(name="post_comment")
	private String postComment;
	

	
	@Column(name="category_id")
	private Long categoryId;

	public PostEntity(@NonNull String postTitle, String postImage, String postComment, Long categoryId) {
		this.postTitle = postTitle;
		this.postImage = postImage;
		this.postComment = postComment;
		this.categoryId = categoryId;
	}
	
	



}
