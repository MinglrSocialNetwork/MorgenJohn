import { ElementRef, HostListener,Input, Component, OnInit, ViewChild } from '@angular/core';
import { CommentService } from '../service/comment.service';
import { FormControl, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {
  comments:Object[];

  constructor(private commentService:CommentService,public element:ElementRef) { }

  @Input() parentPostId: number; // decorate the property with @Input()
  @ViewChild('textCommentForm') textCommentForm: any;

  onSubmit(){
    if(this.textCommentForm.valid){
      //this.commentService.createComment(this.textCommentForm.value).subscribe();
      console.log(this.textCommentForm.value);
      this.textCommentForm.reset();
     
    }
    setTimeout(() => this.loadComments(), 300);
  }

  addComment(){
    console.log("adding comment");
  }

  deleteComment(){
    console.log("deleting comment");
  }
  
  loadComments(): void {
    this.commentService.getComments().subscribe((data) => 
    {
      this.comments = data;
    //  console.log(data);
      // if (data.length > 0) {
      //   for (let item of data) {
      //     this.comments.unshift(item);
      //   }
      // }
    })
    console.log("Finished loading comments");
  }
  
  //Everything below is used to perform textarea autosize
  @HostListener('input',['$event.target'])
  onInput(textarea:HTMLTextAreaElement):void{
      this.adjust();
  }

  adjust():void{
    const textArea = this.element.nativeElement.getElementsByTagName('textarea')[0];
    textArea.style.overflow = 'hidden';
    textArea.style.height = 'auto';
    textArea.style.height = textArea.scrollHeight + 'px';
    textArea.style.maxHeight = '200px';
  }

  ngOnInit():void {
    this.loadComments();
    //console.log(this.parentPostId);
  }
}
