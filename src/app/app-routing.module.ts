import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CardcontainerComponent } from './modules/muzix/components/cardcontainer/cardcontainer.component';
import { WishlistComponent } from './modules/muzix/components/wishlist/wishlist.component';
import { RegisterComponent } from './modules/authentication/components/register/register.component';
import { LoginComponent } from './modules/authentication/components/login/login.component';
import {AuthGuardService} from './modules/muzix/auth-guard.service';

const routes: Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"register",
    component:RegisterComponent
  },
  {
    path:"India",
    component:CardcontainerComponent,
    data:{country:"India"},
    canActivate:[AuthGuardService]
  },
  {
    path:"Spain",
    component:CardcontainerComponent,
    data:{country:"Spain"},
    canActivate:[AuthGuardService]
  },
  {
    path:"WishList",
    component:WishlistComponent,
    canActivate:[AuthGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
