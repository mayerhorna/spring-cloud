import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { OAuthService } from 'angular-oauth2-oidc';
 
@Injectable()
export class AppService {
 
  constructor(
    private _router: Router, private _http: HttpClient, private oauthService: OAuthService){
        this.oauthService.configure({         
            loginUrl: 'http://auth-server:9000/oauth2/authorize',
            redirectUri: 'http://127.0.0.1:4200',
            clientId: 'sentencewebmobile',
            scope: 'openid' ,
            responseType: 'code',
            showDebugInformation: true
        });
        this.oauthService.setStorage(sessionStorage);
        debugger;
        this.oauthService.tryLogin({});      
    }
 
  obtainAccessToken(){
      this.oauthService.responseType = "code"; 
      this.oauthService.requireHttps = false;
      this.oauthService.initImplicitFlow();
  }

  getResource(resourceUrl) : Observable<any>{
    var headers = new HttpHeaders({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8', 'Authorization': 'Bearer '+this.oauthService.getAccessToken()});
    return this._http.get(resourceUrl, { headers: headers })
                   .catch((error:any) => Observable.throw(error.json().error || 'Server error'));
  }

  isLoggedIn(){
console.log(this.oauthService.getAccessToken());  
    if (this.oauthService.getAccessToken() === null){
       return false;
    }
    return true;
  } 

  logout() {
      this.oauthService.logOut();
      location.reload();
  }
}