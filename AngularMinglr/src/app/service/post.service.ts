import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {

url: string = 'http://localhost:8080/Minglr/post/';

  constructor(private http: HttpClient) { }

  getPosts(): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'selectAllPosts');
  }
  
  createTextPost(data: any){
    return this.http.post(this.url + 'createPost', data);
  }

  deletePost(post: any) {
    let id: number = post["id"];
    return this.http.delete(this.url + 'posts/deletePost/' + id, post);
  }

  upvotePost(post: any,userId:any) {
//    let id: number = post["id"];
    console.log(userId);
    return this.http.put(this.url + 'posts/upvotePost/' + userId, post);
  }

  downvotePost(post: any, userId:any) {
 //   let id: number = post["id"];
    console.log(userId);
    return this.http.put(this.url + 'posts/downvotePost/' + userId, post);
  }

  getVotes(userId): Observable<any[]> {
    return this.http.get<any[]>(this.url + 'selectAllVotes/'+ userId);
  }
}

