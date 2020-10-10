package com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.PostRepo;
import com.dao.VoteRepo;
import com.models.Posts;
import com.models.Vote;

@Controller("PostController")
@RequestMapping(value = "/post")
@CrossOrigin(origins = "*") //will change 
public class PostController {
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private VoteRepo voteRepo;
	
	@GetMapping(value = "/selectAllPosts", produces="application/json")
	public @ResponseBody List<Posts> selectAllPosts(){
		
		List<Posts> posts = postRepo.selectAllPosts();
//		System.out.println("Retrieving all posts.... " + posts);
		
		return posts;
		
	}


	@PostMapping(value = "/createPost")
	public @ResponseBody String createPost(@RequestBody Posts post ) {
		
//		System.out.println("Creating new posts " + post );
		System.out.println(post.getImage());
		postRepo.createPost(post);
		
		return "";
		
	}
	

	
	@PutMapping(value = "/posts/updatePost/{postid}")
	public ResponseEntity<Posts> updatePost (@PathVariable int postid , @RequestBody Posts post) {
		
		System.out.println("Updating post.....");
		
	
		Posts updatedPost = postRepo.updatePost(postid , post);
		return new ResponseEntity<Posts>(updatedPost, HttpStatus.OK) ;
		
		
	}
	
	
	@DeleteMapping(value = "/posts/deletePost/{postid}")
    public ResponseEntity<Void> deletePost(@PathVariable int postid) {
		
		System.out.println("Deleting post....");

//		
//		if (post == null)
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		postRepo.deletePost(postid);
		System.out.println(postid);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/posts/upvotePost/{userId}")
    public ResponseEntity<Void> upVotePost(@PathVariable int userId, @RequestBody Posts post ) {
		
	
		Vote vote = new Vote(0,userId,post.getId(),post.getUpvote(), post.getDownvote());
		System.out.println("-------------------------------------------- ");
		System.out.println("Updating upvote post.... "+ userId);
		System.out.println("-------------------------------------------- ");
		System.out.println("Vote table:");
		System.out.println(vote);
		System.out.println("-------------------------------------------- ");
		System.out.println("Posts table:");
		System.out.println(post);
		
		voteRepo.createVotebyUser(vote);
		postRepo.increaseUpvotes(post.getId(), post.getUpvote());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping(value = "/posts/downvotePost/{userId}")
    public ResponseEntity<Void> downVotePost(@PathVariable int userId, @RequestBody Posts post) {
		
		System.out.println("Updating down vote post.... "+ userId);	
		Vote vote = new Vote(0,userId,post.getId(),post.getDownvote(), post.getDownvote());
		
		voteRepo.createVotebyUser(vote);
		postRepo.increaseDownVotes(post.getId(), post.getDownvote());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/selectAllVotes/{userId}", produces="application/json")
	public @ResponseBody List<Vote> selectAllVotes(@PathVariable int userId){
		System.out.println(userId);
		List<Vote> votes = voteRepo.selectAllVote(userId);
		System.out.println(votes);
//		System.out.println("Retrieving all posts.... " + posts);
		
		return votes;	
	}

	
//	public static void main(String[] args) {
//		
//		PostController PC = new PostController();
//		PC.deletePost(0, null);
//		
//	}	
}
