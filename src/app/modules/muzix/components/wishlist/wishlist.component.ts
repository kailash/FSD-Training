import { Component, OnInit } from '@angular/core';
import { MuzixService } from '../../muzix.service';
import {Track} from '../../track';
import {MatSnackBar} from '@angular/material';
import { error } from 'selenium-webdriver';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  tracks:Array<Track>;
  wishData=true;
  statuscode:number;
  constructor(
    private muzixService:MuzixService,
    private matSnackbar : MatSnackBar) {
      this.tracks=[];
     }

  ngOnInit() {
    const message="Wishlist is empty !!!!";
    this.muzixService.getAllTracksForWishList().subscribe(data =>{
      this.tracks=data;
      if(data.length ===0){
        this.matSnackbar.open(message,"",{
          duration:1000
        });
      }
    });
  }

  deleteTrackFromWishList(track){
    console.log("wishlist component",track);
    this.muzixService.deleteTrackfromWishList(track).subscribe(data =>{
      console.log("deleted",track);
      const index=this.tracks.indexOf(track);
      this.tracks.splice(index,1);
      this.matSnackbar.open("deleted. ","",{
        duration:1000
      });
    });
    return this.tracks;
  }

  updateComments(track){
    console.log("wishlist :",track)
    this.muzixService.updateComment(track).subscribe((data)=>{
      this.matSnackbar.open("Successfully updated","",{
        duration:1000
      });
    },
    error =>{
      console.log("error",error);
    }
    );
  }

}
