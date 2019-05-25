import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {User} from '../authentication/User';
export const USER_NAME="username";
export const TOKEN_NAME="jwt_token";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private springRegisterEndpoint:string;
  private springSaveEndpoint:string;
  private loginEndPoint:string;

  constructor(private httpClient:HttpClient) { 
   // this.springRegisterEndpoint="http://localhost:8085/api/v1/usertrackservice/";
   this.springRegisterEndpoint="http://localhost:8086/usertrackservice/api/v1/usertrackservice/register";
  // this.springRegisterEndpoint="http://localhost:8086/orchestrationservice/api/v1/user";
    //this.springSaveEndpoint="http://localhost:8084/api/v1/userservice/";
    this.loginEndPoint="http://localhost:8086/authenticationservice/api/v1/userservice/";
  }

  registerUser(newUser: User){
    const url=this.springRegisterEndpoint;
    return this.httpClient.post(url,newUser,{observe:"response"});
  }

 // saveuser(newUser:User){
   /// const url=this.springSaveEndpoint+"save";
   // return this.httpClient.post(url,newUser);
 // }

  loginUser(newUser:User){
    const url=this.loginEndPoint+"login";
    sessionStorage.setItem(USER_NAME,newUser.username);
    return this.httpClient.post(url,newUser,{observe:"response"});
  }

  getToken(){
    return localStorage.getItem(TOKEN_NAME);
  }

  isTokenExpired(token?:string):boolean{
    if(localStorage.getItem(TOKEN_NAME)){
      return true;
    }else{
      return false;
    }
  }

  logout(){
    localStorage.removeItem(TOKEN_NAME);
    localStorage.clear();
    sessionStorage.removeItem(USER_NAME);
    sessionStorage.clear();
  }
}
