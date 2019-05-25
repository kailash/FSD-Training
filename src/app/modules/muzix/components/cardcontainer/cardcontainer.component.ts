import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material'

import { Track } from './../../track';
import { Artist } from './../../artist';
import { Image } from './../../image';
import { MuzixService } from '../../muzix.service';


@Component({
  selector: 'app-cardcontainer',
  templateUrl: './cardcontainer.component.html',
  styleUrls: ['./cardcontainer.component.css']
})
export class CardcontainerComponent implements OnInit {
  tracks: Array<Track>;
  trackObj: Track;
  artistObj: Artist;
  imageObj: Image
  countryName: string;
  id: number;
  statusCode: number;
  errorStatus: string;;
  artistName:string;
  searchTrack:Array<Track>;
  constructor(
    private muzixService: MuzixService,
    private routes: ActivatedRoute,
    private matSnackbar: MatSnackBar) {
    this.tracks = [];
  }

  ngOnInit() {
    const tempData = this.routes.data.subscribe(newData => {
      this.countryName = newData.country;
    });

    this.muzixService.getAllTrackDetails(this.countryName).subscribe(tracks => {
      const data = tracks['tracks']['track'];
      this.tracks = [];
      this.id = 0;
      data.forEach(targetData => {
        this.id++;
        this.trackObj = new Track();
        this.artistObj = new Artist();
        this.artistObj = targetData["artist"];
        this.imageObj = new Image();
        this.imageObj.text = targetData["image"][2]["#text"];
        this.imageObj.size = targetData["image"][2]["#size"];
        this.trackObj = targetData;
        this.trackObj.artist = this.artistObj;
        this.artistObj.image = this.imageObj;
        this.trackObj.trackId = this.countryName.slice(0, 3) + this.id;

        this.tracks.push(this.trackObj);
        this.searchTrack=this.tracks;
      });
    });
  }

  onKey(event:any){
    this.artistName=event.target.value;
    const result=this.searchTrack.filter(track =>{
      return track.artist.name.match(this.artistName);
    });
    this.tracks=result;
  }

  addToWishList(track) {
    console.log('inside card container', track);
    this.muzixService.addToWishList(track).subscribe(data => {
      this.statusCode = data.status;
      if (this.statusCode === 201) {
        this.matSnackbar.open("Added to Wish-List successfully !!!", "", {
          duration: 1000
        });
      }
    },
      error => {
        console.log("error", error);
        this.errorStatus = `${error.status}`;
        console.log("error status", this.errorStatus);
        const errorMsg = `${error.error}`;
        console.log("error messgae", errorMsg);
        this.statusCode = parseInt(this.errorStatus, 10);
        if (this.statusCode === 409) {
          this.matSnackbar.open(errorMsg, "", {
            duration: 1000
          });
          this.statusCode = 0;
        }
      });
  }
}
