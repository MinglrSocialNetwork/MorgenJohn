package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.CommentRepo;
import com.models.Comment;
import com.models.Posts;


@RestController
@RequestMapping(value = "/comment")
@CrossOrigin(origins = "*") //will change 
public class CommentController {

	@Autowired
	private CommentRepo commentRepo;

	//The real select Comment
	@GetMapping(value = "/selectAllComment")
	public List<Comment> selectAllComments(){
		return commentRepo.selectAllComments();
	}
	
	@PostMapping(value = "/createComment")
	public void createComment(@RequestBody Comment comment ) {
		
		Comment c = new Comment(5,1,"Oppan Gangnam Style Gangnam Style Op op"
				+ "op op oppan Gangnam Style Gangnam Style Op op op op oppan Gangnam Style.");
		System.out.println("Creating new comment " + comment );
		//System.out.println(c);
		commentRepo.createComment(comment);		
	}
		
}
