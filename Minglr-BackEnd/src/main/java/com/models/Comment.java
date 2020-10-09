package com.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "comment_table")
public class Comment {

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int commentId;
	
	@Column(name ="post_id")
	private int postId;
	
	@Column(name ="comment_text")
	private String commentText;

	public Comment() {
		super();
	}
	
	public Comment(int postId, int commentId, String commentText) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.commentText = commentText;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", postId=" + postId + ", commentText=" + commentText + "]";
	}
}
