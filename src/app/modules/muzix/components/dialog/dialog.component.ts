import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Track } from '../..//track';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  comments:string;
  constructor(private matDialogRef:MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA)public data: Track) { 
      this.comments=data.comments;
    }

  ngOnInit() {
  }

  onNoClick(){
    this.matDialogRef.close();
  }

  updateComments(){
    this.matDialogRef.close(this.comments);
  }
}
