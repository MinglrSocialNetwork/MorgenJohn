import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }
  
  url: string = 'http://localhost:8080/Minglr/comment/';

  getComments(): Observable<any[]> {
   // console.log("I am getting a comment");
    return this.http.get<any[]>(this.url + 'selectAllComment');;
  }

  createComment(data: any){
    console.log(data);
    return this.http.post(this.url + 'createComment', data);
  }

  deleteComment(commment: any) {
    let id: number = commment["id"];
    return this.http.delete(this.url + 'comment/deleteComment/' + id, commment);
  }
}
