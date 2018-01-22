import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { CryptoComponent } from './crypto/crypto.component';
import { CryptoListComponent } from './crypto/crypto-list/crypto-list.component';
import {CryptoService} from "./crypto/crypto.service";
import {HttpModule} from "@angular/http";


@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    CryptoComponent,
    CryptoListComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [CryptoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
