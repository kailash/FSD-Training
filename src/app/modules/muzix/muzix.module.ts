import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardcontainerComponent } from './components/cardcontainer/cardcontainer.component';
import { CardComponent } from './components/card/card.component';
import { AppRoutingModule } from '../../app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {AngularmaterialModule} from '../angularmaterial/angularmaterial.module';
import { HeaderComponent } from './components/header/header.component';
import { WishlistComponent } from './components/wishlist/wishlist.component';
import { FooterComponent } from './components/footer/footer.component';
import { DialogComponent } from './components/dialog/dialog.component';
import { MuzixService } from 'src/app/modules/muzix/muzix.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { InterceptorService } from 'src/app/modules/muzix/interceptor.service';


@NgModule({
  declarations: [CardcontainerComponent, CardComponent, HeaderComponent, WishlistComponent, FooterComponent, DialogComponent],
  imports: [
    BrowserAnimationsModule,
    CommonModule,
    AppRoutingModule,
    AngularmaterialModule
  ],
  exports: [
    CardcontainerComponent,
    HeaderComponent,
    AppRoutingModule,
    FooterComponent,
    WishlistComponent
  ],
  providers:[
    MuzixService,
    {
      provide:HTTP_INTERCEPTORS,
      useClass:InterceptorService,
      multi:true
    }
  ],
  entryComponents: [
    DialogComponent
  ]
})
export class MuzixModule { }
