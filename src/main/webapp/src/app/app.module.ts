import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule, HttpClient} from "@angular/common/http";

import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { CryptoComponent } from './crypto/crypto.component';
import { CryptoListComponent } from './crypto/crypto-list/crypto-list.component';
import {CryptoService} from "./crypto/crypto.service";
import {HttpModule} from "@angular/http";
import { CryptoChartComponent } from './crypto/crypto-chart/crypto-chart.component';
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    CryptoComponent,
    CryptoListComponent,
    CryptoChartComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    BrowserAnimationsModule,
    FormsModule,
  ],
  providers: [CryptoService, CryptoChartComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
