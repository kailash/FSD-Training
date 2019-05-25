import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Track } from './track';
import { HttpHeaders } from '@angular/common/http';
export const USER_NAME = "username";

@Injectable({
  providedIn: 'root'
})
export class MuzixService {
  thirdpartyAPI: string;
  apiKey: string;
  springBackEnd: string;
  userName: string;

  constructor(private httpClient: HttpClient) {
    this.thirdpartyAPI = 'http://ws.audioscrobbler.com/2.0/?method=geo.gettoptracks&country=';
    this.apiKey = '&api_key=5f45b201d21271c60b94a1cc24edc9d2&format=json';
    this.springBackEnd = 'http://localhost:8086/usertrackservice/api/v1/usertrackservice/';
  }

  getAllTrackDetails(country: string): Observable<any> {
    const url = `${this.thirdpartyAPI}${country}${this.apiKey}`;
    return this.httpClient.get(url);
  }

  addToWishList(track: Track) {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springBackEnd + "user/" + this.userName + "/track";
    return this.httpClient.post(url, track, {
      observe: "response"
    });
  }

  getAllTracksForWishList(): Observable<Track[]> {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springBackEnd + "user/" + this.userName + "/tracks";
    return this.httpClient.get<Track[]>(url);
  }

  deleteTrackfromWishList(track: Track) {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springBackEnd + "user/" + this.userName + "/"+track.trackId;
    //const options = {
     // headers: new HttpHeaders({
      //  'Content-Type': 'application/json',
     // }),
     // body: track
  //  }
    return this.httpClient.delete(url);

  }

  updateComment(track: Track) {
    this.userName = sessionStorage.getItem(USER_NAME);
    const url = this.springBackEnd + "user/" + this.userName + "/track";
    return this.httpClient.patch(url, track, {
      observe: "response"
    });

  }

  filterArtist(tracks: Array<Track>, artistName: string) {
    const results = tracks.filter(track => {
      return track.artist.name.match(artistName);
    });
    return results;
  }
}
