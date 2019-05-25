import { Component, OnInit } from '@angular/core';
import { User } from '../../User';
import {AuthenticationService} from '../../authentication.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  user: User;
  constructor(private authService: AuthenticationService,
     private snackbar:MatSnackBar,
     private router:Router) {
    this.user = new User();
  }

  register() {
    this.authService.registerUser(this.user).subscribe(data =>{
      if(data.status===201){
          this.snackbar.open("successfully registered","",{
            duration:1000
          });
        //  this.authService.saveuser(this.user).subscribe(data=>{
         //   console.log("saved successfully");
         // })
      }
      this.router.navigate(["/login"]);
    },error =>{
      if(error.status===409){
        const errorMsg=error.error.errorMsg;
        this.snackbar.open(errorMsg,"",{
          duration:1000
        });
      }
      
    });
  }
  ngOnInit() {
  }

}
