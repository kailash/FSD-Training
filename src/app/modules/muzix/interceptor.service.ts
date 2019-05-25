import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../authentication/authentication.service';

@Injectable({
  providedIn: 'root'
})


export class InterceptorService implements HttpInterceptor {
  constructor(private authService:AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request =request.clone({
      setHeaders:{
        Authorization: `Bearer ${this.authService.getToken()}`
      }
    });
    return next.handle(request);

  }

 
}
