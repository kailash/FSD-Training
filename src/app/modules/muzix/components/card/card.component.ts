import { Component, OnInit, Input, Output , EventEmitter} from '@angular/core';
import { Track } from '../../track';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { DialogComponent } from 'src/app/modules/muzix/components/dialog/dialog.component';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input()
  track:Track;
  @Input()
  wishData:boolean;
  @Output()
  addToWishList=new EventEmitter();
  @Output()
  deleteTrackFromWishList=new EventEmitter();
  @Output()
  updateComments=new EventEmitter();
  
  constructor(private dialog:MatDialog) { }

  ngOnInit() {
  }

  addButtonClick(track){
    console.log(track);
    this.addToWishList.emit(track);
  }

  deleteButtonClick(track){
    console.log("card component ",track);
    this.deleteTrackFromWishList.emit(track);
  }

  updateCommentClick(){
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data:{comments:this.track.comments}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('result comment',result);
      this.track.comments=result;
      this.updateComments.emit(this.track);
    });
  }
}
